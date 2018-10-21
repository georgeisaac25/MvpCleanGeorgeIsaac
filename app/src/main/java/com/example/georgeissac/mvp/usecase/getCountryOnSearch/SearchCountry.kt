package com.example.georgeissac.mvp.usecase.getCountryOnSearch

import android.arch.lifecycle.LiveData
import com.example.georgeissac.mvp.room.MyRepository
import com.example.georgeissac.mvp.usecase.getCountry.response.Country
import com.example.georgeissac.mvp.usecase.getCountryOnSearch.request.Request
import io.reactivex.Maybe
import io.reactivex.Observable

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