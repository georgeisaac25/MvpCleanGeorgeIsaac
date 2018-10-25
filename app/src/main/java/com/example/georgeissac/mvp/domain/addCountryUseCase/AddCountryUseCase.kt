package com.example.georgeissac.mvp.domain.addCountryUseCase

import com.example.georgeissac.mvp.data.DataRepository
import com.example.georgeissac.mvp.domain.addCountryUseCase.request.RequestAddCountry
import com.example.georgeissac.mvp.domain.addCountryUseCase.response.ResponseOfAddCountry
import com.example.georgeissac.mvp.database.CountryPojo
import io.reactivex.Observable

class AddCountryUseCase(val dataRepository: DataRepository){
    fun addCountries(countryList :  List<CountryPojo>) : Observable<ResponseOfAddCountry> {
        val request = RequestAddCountry(countryList)
        return dataRepository.putAllCountry(request.addCountryList)
    }
}