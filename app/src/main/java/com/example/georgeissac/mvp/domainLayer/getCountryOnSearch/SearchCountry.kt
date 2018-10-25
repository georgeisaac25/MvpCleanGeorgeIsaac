package com.example.georgeissac.mvp.domainLayer.getCountryOnSearch

import android.arch.lifecycle.LiveData
import com.example.georgeissac.mvp.dataLayer.database.MyRepository
import com.example.georgeissac.mvp.domainLayer.getCountry.CountryPojo
import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country
import com.example.georgeissac.mvp.domainLayer.getCountryOnSearch.request.Request
import io.reactivex.Maybe

class SearchCountry(var myRepository : MyRepository){
    fun getCountry(searchString: String) : Maybe<List<CountryPojo>> {
        val request = Request(searchString)
        return myRepository.searchByTextUsingRx(request.searchString)
    }
}