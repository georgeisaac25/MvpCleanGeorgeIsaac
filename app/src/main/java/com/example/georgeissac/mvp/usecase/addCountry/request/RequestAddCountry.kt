package com.example.georgeissac.mvp.usecase.addCountry.request

import com.example.georgeissac.mvp.usecase.getCountry.response.Country

class RequestAddCountry(countryList :  List<Country>){
    var addCountryList : List<Country>
    init {
        addCountryList = countryList
    }
}