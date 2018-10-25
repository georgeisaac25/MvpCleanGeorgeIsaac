package com.example.georgeissac.mvp.retrofit

import com.example.georgeissac.mvp.domain.countryUseCase.response.Country
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("rest/v2/all/")
    fun getCountries() : Call<List<Country>>

}