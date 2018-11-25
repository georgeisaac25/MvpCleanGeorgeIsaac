package com.example.data.datasource

import com.example.data.database.LocalDataSource
import com.example.domain.domain.countryUseCase.interfaces.RepositoryInterface
import com.example.data.database.CountryEntity
import com.example.domain.domain.CountryPojo
import com.example.data.remote.CountryPojoMapper
import com.example.georgeissac.mvp.domain.countryUseCase.response.Country
import com.example.domain.domain.interfaces.RepositoryContract
import io.reactivex.Maybe
import com.example.georgeissac.mvp.domain.searchCountryUseCase.request.Request
import com.example.data.remote.RemoteDataSource
import io.reactivex.MaybeSource
import io.reactivex.functions.Function
import java.util.concurrent.Callable

class DataRepository(val localDataSource: LocalDataSource, val remoteDataSource: RemoteDataSource) :
    RemoteDataSourceInterface, RepositoryContract {

    lateinit var repositoryInterface: RepositoryInterface

    override fun searchCountryInDb(request: Request): Maybe<List<CountryPojo>> {
        val mapper = CountryModelMapperImpl()
        return localDataSource.searchByTextUsingRx(request.searchString)
            .map { mapper.fromEntity(it) }
    }

    override fun getCountryRx(): Maybe<List<CountryPojo>> {


        val list = localDataSource.all()
        val mapper = CountryModelMapperImpl()
        if (!list.isEmpty()) {
            return localDataSource.getAllCountryUsingRx()
                .map { mapper.fromEntity(it) }
        } else {
            return remoteDataSource.getCountriesUsingMaybe()
                .flatMap(object : Function<List<Country>, MaybeSource<List<CountryPojo>>> {
                    override fun apply(listCountry: List<Country>): MaybeSource<List<CountryPojo>> {
                        val listAfterMapping: List<CountryEntity> =
                            CountryPojoMapper().changeToCountryEntity(listCountry)
                        localDataSource.insertData(listAfterMapping)
                        return getCountryAfterInsertion(listAfterMapping)
                    }

                })
        }
    }

    override fun getCountries(repositoryInterface: RepositoryInterface) {
        this.repositoryInterface = repositoryInterface

        val list = localDataSource.all()

        if (!list.isEmpty()) {
            val mapper = CountryModelMapperImpl()
            this.repositoryInterface.setResultWhenSucess(mapper.fromEntity(list))
        } else {
            remoteDataSource.getCountries(this)
        }
    }

    override fun setResultWhenSuccess(list: List<Country>) {
        val listAfterMapping: List<CountryEntity> = CountryPojoMapper().changeToCountryEntity(list)
        Thread(Runnable {
            localDataSource.insertData(listAfterMapping)
        }).start()
        val mapper = CountryModelMapperImpl()
        repositoryInterface.setResultWhenSucess(mapper.fromEntity(listAfterMapping))
    }

    override fun setResultWhenFailed(error: String) {
        repositoryInterface.setResultWhenFailed(error)
    }

    fun getCountryAfterInsertion(list: List<CountryEntity>): Maybe<List<CountryPojo>> {
        return Maybe.fromCallable(Callable<List<CountryPojo>> {
            val mapper = CountryModelMapperImpl()
            return@Callable mapper.fromEntity(list)
        })
    }

}