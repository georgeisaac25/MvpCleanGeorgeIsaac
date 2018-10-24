package com.example.georgeissac.mvp.presentationLayer

import android.arch.lifecycle.LiveData
import com.example.georgeissac.mvp.domainLayer.addCountry.AddCountry
import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country
import com.example.georgeissac.mvp.domainLayer.getCountryOnSearch.SearchCountry

interface UseCaseInterface {
    fun addCountries(list : List<Country>)
    fun getCountyList()
    fun searchCountry(string: String)
}