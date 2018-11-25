package com.example.data.remote

import com.example.data.database.CountryEntity
import com.example.georgeissac.mvp.domain.countryUseCase.response.Country

class CountryPojoMapper {
    fun changeToCountryEntity(listOfCountry: List<Country>): List<CountryEntity> {
        val list: ArrayList<CountryEntity> = ArrayList()
        for (country in listOfCountry) {
            val countryPojo = CountryEntity()
            countryPojo.name = country.name
            countryPojo.alpha2Code = country.alpha2Code
            countryPojo.alpha3Code = country.alpha3Code
            countryPojo.flag = country.flag
            list.add(countryPojo)
        }
        return list
    }
}