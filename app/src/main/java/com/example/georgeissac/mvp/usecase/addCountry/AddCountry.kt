package com.example.georgeissac.mvp.usecase.addCountry

import com.example.georgeissac.mvp.room.MyRepository
import com.example.georgeissac.mvp.usecase.addCountry.request.RequestAddCountry
import com.example.georgeissac.mvp.usecase.getCountry.response.Country
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.Callable

class AddCountry(data : MyRepository){
    private var myRepository: MyRepository = data

    fun addCountries(countryList :  List<Country>) : Observable<ResponseOfAddCountry> {
        val request = RequestAddCountry(countryList)
        return putAllCountry(request.addCountryList)
    }

    fun putAllCountry( list : List<Country> ) : Observable<ResponseOfAddCountry>{
        return Observable.fromCallable(object : Callable<ResponseOfAddCountry>{
            override fun call(): ResponseOfAddCountry {
                val size = myRepository.insertData(list).size
                return ResponseOfAddCountry(size)
            }

        })
    }


}