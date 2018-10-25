package com.example.georgeissac.mvp.domain.addCountryUseCase.response

class ResponseOfAddCountry (val count : Int){

    fun getSuccessCount() : Int{
        return count
    }
}
