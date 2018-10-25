package com.example.georgeissac.mvp.domainLayer.getCountryOnSearch

import com.example.georgeissac.mvp.dataLayer.DataRepository
import com.example.georgeissac.mvp.domainLayer.getCountry.CountryPojo
import com.example.georgeissac.mvp.domainLayer.getCountryOnSearch.request.Request
import io.reactivex.Maybe

class SearchCountry(var dataRepository: DataRepository){
    fun getCountry(searchString: String) : Maybe<List<CountryPojo>> {
        val request = Request(searchString)
        return dataRepository.searchCountryInDb(request)
    }
}