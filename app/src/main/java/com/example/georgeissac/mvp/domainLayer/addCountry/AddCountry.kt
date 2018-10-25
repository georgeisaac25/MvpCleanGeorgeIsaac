package com.example.georgeissac.mvp.domainLayer.addCountry

import com.example.georgeissac.mvp.dataLayer.DataRepository
import com.example.georgeissac.mvp.domainLayer.addCountry.request.RequestAddCountry
import com.example.georgeissac.mvp.domainLayer.getCountry.CountryPojo
import io.reactivex.Observable

class AddCountry(val dataRepository: DataRepository){
    fun addCountries(countryList :  List<CountryPojo>) : Observable<ResponseOfAddCountry> {
        val request = RequestAddCountry(countryList)
        return dataRepository.putAllCountry(request.addCountryList)
    }
}