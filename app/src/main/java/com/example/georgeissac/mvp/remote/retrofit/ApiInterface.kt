package com.example.georgeissac.mvp.retrofit

import com.example.georgeissac.mvp.domain.countryUseCase.response.Country
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("rest/v2/all/")
    fun getCountries() : Observable<List<Country>>
}