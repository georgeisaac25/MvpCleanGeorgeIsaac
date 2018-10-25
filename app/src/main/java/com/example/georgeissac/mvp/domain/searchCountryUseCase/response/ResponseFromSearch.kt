package com.example.georgeissac.mvp.domain.searchCountryUseCase.response

import com.example.georgeissac.mvp.domain.countryUseCase.response.Country

class ResponseFromSearch(var list: List<Country>){
    
    fun getResponse(): List<Country>{
        return list
    }
}