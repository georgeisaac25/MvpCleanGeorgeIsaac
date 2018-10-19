package com.example.georgeissac.mvp.usecase.getCountryOnSearch

import android.arch.lifecycle.LiveData
import com.example.georgeissac.mvp.room.MyRepository
import com.example.georgeissac.mvp.usecase.getCountry.response.Country
import com.example.georgeissac.mvp.usecase.getCountryOnSearch.request.Request

class SearchCountry(data : MyRepository){

    private var myRepository: MyRepository = data
    fun getCountry(searchString: String) : LiveData<List<Country>> {
        val request = Request(searchString)
        return myRepository.searchByText(request.searchString)
    }
}