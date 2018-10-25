package com.example.georgeissac.mvp.domainLayer.addCountry.request

import com.example.georgeissac.mvp.domainLayer.getCountry.CountryPojo

class RequestAddCountry(countryList :  List<CountryPojo>){
    var addCountryList : List<CountryPojo> = countryList
}