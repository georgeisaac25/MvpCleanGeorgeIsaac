package com.example.georgeissac.mvp.domainLayer.getCountryOnSearch

import android.arch.lifecycle.LiveData
import com.example.georgeissac.mvp.dataLayer.database.MyRepository
import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country
import com.example.georgeissac.mvp.domainLayer.getCountryOnSearch.request.Request
import io.reactivex.Maybe

class SearchCountry(data : MyRepository){

    private var myRepository: MyRepository = data
    fun getCountryUsingLive(searchString: String) : LiveData<List<Country>> {
        val request = Request(searchString)
        return myRepository.searchByText(request.searchString)
    }

    fun getCountry(searchString: String) : Maybe<List<Country>> {
        val request = Request(searchString)
        return myRepository.searchByTextUsingRx(request.searchString)
    }

}