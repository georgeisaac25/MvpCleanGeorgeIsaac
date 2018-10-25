package com.example.georgeissac.mvp.data

import com.example.georgeissac.mvp.database.LocalDataSource
import com.example.georgeissac.mvp.domain.addCountryUseCase.response.ResponseOfAddCountry
import com.example.georgeissac.mvp.database.CountryPojo
import com.example.georgeissac.mvp.remote.Mapper
import com.example.georgeissac.mvp.domain.countryUseCase.repository.CommunicateFromEntityToInteractor
import com.example.georgeissac.mvp.domain.countryUseCase.response.Country
import io.reactivex.Maybe
import com.example.georgeissac.mvp.domain.searchCountryUseCase.request.Request
import com.example.georgeissac.mvp.remote.RemoteDataSource
import io.reactivex.Observable
import java.util.concurrent.Callable


class DataRepository(val localDataSource: LocalDataSource, val remoteDataSource: RemoteDataSource) :
    CommunicateFromRemoteToRepository {

    lateinit var communicateFromEntityToInteractor: CommunicateFromEntityToInteractor
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

    fun callWebService(communicateFromEntityToInteractor: CommunicateFromEntityToInteractor) {
        this.communicateFromEntityToInteractor = communicateFromEntityToInteractor
        remoteDataSource.getCountries(this)
    }

    override fun setResultWhenSuccess(list: List<Country>) {
        var list = Mapper().changeToCountryPojo(list)
        communicateFromEntityToInteractor.setResultWhenSucess(list)
    }

    override fun setResultWhenFailed(error: String) {
        communicateFromEntityToInteractor.setResultWhenFailed(error)
    }

}