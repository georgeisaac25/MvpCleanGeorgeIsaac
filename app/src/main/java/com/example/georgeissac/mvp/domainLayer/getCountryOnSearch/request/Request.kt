package com.example.georgeissac.mvp.domainLayer.getCountryOnSearch.request

class Request(var string: String){
    var searchString : String
    init {
        searchString = string
    }
}