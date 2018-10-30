package com.example.georgeissac.mvp.data

import com.example.georgeissac.mvp.database.LocalDataSource
import com.example.georgeissac.mvp.domain.addCountryUseCase.response.ResponseOfAddCountry
import com.example.georgeissac.mvp.database.CountryPojo
import com.example.georgeissac.mvp.remote.CountryPojoMapper
import com.example.georgeissac.mvp.domain.countryUseCase.interfaces.RepositoryInterface
import com.example.georgeissac.mvp.domain.countryUseCase.response.Country
import io.reactivex.Maybe
import com.example.georgeissac.mvp.domain.searchCountryUseCase.request.Request
import com.example.georgeissac.mvp.remote.RemoteDataSource
import io.reactivex.Observable
import java.util.concurrent.Callable


class DataRepository(val localDataSource: LocalDataSource, val remoteDataSource: RemoteDataSource) :
    RemoteDataSourceInterface {

    lateinit var repositoryInterface: RepositoryInterface
    fun searchCountryInDb(request: Request): Maybe<List<CountryPojo>> {
        return localDataSource.searchByTextUsingRx(request.searchString)
    }

    fun putAllCountry(list: List<CountryPojo>): Observable<ResponseOfAddCountry> {
        return Observable.fromCallable(object : Callable<ResponseOfAddCountry> {
            override fun call(): ResponseOfAddCountry {
                val size = localDataSource.insertData(list).size
                return ResponseOfAddCountry(
                    size
                )
            }
        })
    }

    fun callWebService(repositoryInterface: RepositoryInterface) {
        this.repositoryInterface = repositoryInterface
        remoteDataSource.getCountries(this)
    }

    override fun setResultWhenSuccess(list: List<Country>) {
        val listAfterMapping = CountryPojoMapper().changeToCountryPojo(list)
        repositoryInterface.setResultWhenSucess(listAfterMapping)
    }

    override fun setResultWhenFailed(error: String) {
        repositoryInterface.setResultWhenFailed(error)
    }

}