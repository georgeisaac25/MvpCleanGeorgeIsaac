package com.example.georgeissac.mvp.retrofit

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class ApiClient {


    companion object {
        val BASE_URL = "https://restcountries.eu/"
        private var retrofit: Retrofit? = null


        val retofitClient: Retrofit?
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                }
                return retrofit
            }
    }


}