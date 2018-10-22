package com.example.georgeissac.mvp.domainLayer.getCountryOnSearch.response

import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country

class ResponseFromSearch(var list: List<Country>){
    
    fun getResponse(): List<Country>{
        return list
    }
}