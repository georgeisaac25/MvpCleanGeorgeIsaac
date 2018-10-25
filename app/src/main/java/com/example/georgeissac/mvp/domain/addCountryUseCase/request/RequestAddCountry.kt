package com.example.georgeissac.mvp.domain.addCountryUseCase.request

import com.example.georgeissac.mvp.database.CountryPojo

class RequestAddCountry(countryList :  List<CountryPojo>){
    var addCountryList : List<CountryPojo> = countryList
}