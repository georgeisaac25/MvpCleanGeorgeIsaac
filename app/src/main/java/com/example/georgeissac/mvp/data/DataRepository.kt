package com.example.georgeissac.mvp.data

import com.example.georgeissac.mvp.database.LocalDataSource
import com.example.georgeissac.mvp.database.CountryEntity
import com.example.georgeissac.mvp.domain.CountryPojo
import com.example.georgeissac.mvp.remote.CountryPojoMapper
import com.example.georgeissac.mvp.domain.countryUseCase.interfaces.RepositoryInterface
import com.example.georgeissac.mvp.domain.countryUseCase.response.Country
import com.example.georgeissac.mvp.domain.interfaces.RepositoryContract
import io.reactivex.Maybe
import com.example.georgeissac.mvp.domain.searchCountryUseCase.request.Request
import com.example.georgeissac.mvp.remote.RemoteDataSource

class DataRepository(val localDataSource: LocalDataSource, val remoteDataSource: RemoteDataSource) :
    RemoteDataSourceInterface, RepositoryContract {

    lateinit var repositoryInterface: RepositoryInterface

    override fun searchCountryInDb(request: Request): Maybe<List<CountryPojo>> {
        val mapper = CountryModelMapperImpl()
        return localDataSource.searchByTextUsingRx(request.searchString)
            .map { mapper.fromEntity(it) }
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

}