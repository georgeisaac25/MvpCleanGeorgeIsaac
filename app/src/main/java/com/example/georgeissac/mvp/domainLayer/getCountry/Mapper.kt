package com.example.georgeissac.mvp.domainLayer.getCountry

import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country

class Mapper {
    fun changeToCountryPojo(listOfCountry: List<Country>): List<CountryPojo> {
        val list: ArrayList<CountryPojo> = ArrayList()
        for (country in listOfCountry) {
            val countryPojo = CountryPojo()
            countryPojo.name = country.name
            countryPojo.alpha2Code = country.alpha2Code
            countryPojo.alpha3Code = country.alpha3Code
            countryPojo.flag = country.flag
            list.add(countryPojo)
        }
        return list
    }
}