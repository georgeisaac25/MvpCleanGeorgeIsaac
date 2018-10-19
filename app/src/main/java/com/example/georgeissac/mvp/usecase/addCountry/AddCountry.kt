package com.example.georgeissac.mvp.usecase.addCountry

import com.example.georgeissac.mvp.room.MyRepository
import com.example.georgeissac.mvp.usecase.addCountry.request.RequestAddCountry
import com.example.georgeissac.mvp.usecase.getCountry.response.Country

class AddCountry(data : MyRepository){
    private var myRepository: MyRepository = data

    fun addCountries(countryList :  List<Country>)  {
        val request = RequestAddCountry(countryList)
        return myRepository.insertData(request.addCountryList)
    }
}