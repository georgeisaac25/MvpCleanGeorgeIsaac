package com.example.georgeissac.mvp.dataLayer

import com.example.georgeissac.mvp.dataLayer.database.LocalDataSource
import com.example.georgeissac.mvp.domainLayer.addCountry.ResponseOfAddCountry
import com.example.georgeissac.mvp.domainLayer.getCountry.CountryPojo
import io.reactivex.Maybe
import com.example.georgeissac.mvp.domainLayer.getCountryOnSearch.request.Request
import io.reactivex.Observable
import java.util.concurrent.Callable


class DataRepository(val localDataSource: LocalDataSource, val remoteDataSource: RemoteDataSource){

    fun searchCountryInDb(request : Request) : Maybe<List<CountryPojo>> {
        return localDataSource.searchByTextUsingRx(request.searchString)
    }

    fun putAllCountry( list : List<CountryPojo> ) : Observable<ResponseOfAddCountry> {
        return Observable.fromCallable(object : Callable<ResponseOfAddCountry> {
            override fun call(): ResponseOfAddCountry {
                val size = localDataSource.insertData(list).size
                return ResponseOfAddCountry(size)
            }
        })
    }


}