package com.example.georgeissac.mvp.presentationLayer

import android.arch.lifecycle.LiveData
import com.example.georgeissac.mvp.domainLayer.addCountry.AddCountry
import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country
import com.example.georgeissac.mvp.domainLayer.getCountryOnSearch.SearchCountry

interface UseCaseInterface {
    fun searchCountryUsingLiveData(string: String, searchCountry: SearchCountry) : LiveData<List<Country>>
    fun addCountries(list : List<Country>,addCountry: AddCountry)
    fun getCountyList()
    fun searchCountry(string: String, searchCountry: SearchCountry)
}