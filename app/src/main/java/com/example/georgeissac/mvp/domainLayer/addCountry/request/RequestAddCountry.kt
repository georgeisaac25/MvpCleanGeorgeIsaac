package com.example.georgeissac.mvp.domainLayer.addCountry.request

import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country

class RequestAddCountry(countryList :  List<Country>){
    var addCountryList : List<Country>
    init {
        addCountryList = countryList
    }
}