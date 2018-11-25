package com.example.data.remote.retrofit

import com.example.georgeissac.mvp.domain.countryUseCase.response.Country
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.Call;

interface ApiInterface {
    @GET("rest/v2/all/")
    fun getCountries() : Call<List<Country>>

    @GET("rest/v2/all/")
    fun getCountriesUsingRx() : Maybe<List<Country>>

}