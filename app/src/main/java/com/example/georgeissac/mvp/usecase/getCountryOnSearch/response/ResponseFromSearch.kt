package com.example.georgeissac.mvp.usecase.getCountryOnSearch.response

import com.example.georgeissac.mvp.usecase.getCountry.response.Country

class ResponseFromSearch(var list: List<Country>){
    
    fun getResponse(): List<Country>{
        return list
    }
}