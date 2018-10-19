package com.example.georgeissac.mvp.usecase.getCountryOnSearch.request

class Request(var string: String){
    var searchString : String
    init {
        searchString = string
    }
}