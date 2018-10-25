package com.example.georgeissac.mvp.dataLayer.webservice

import android.util.Log
import com.example.georgeissac.mvp.domainLayer.getCountry.Mapper
import com.example.georgeissac.mvp.retrofit.ApiClient
import com.example.georgeissac.mvp.retrofit.ApiInterface
import com.example.georgeissac.mvp.domainLayer.getCountry.repository.CommunicateFromEntityToInteractor
import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WebserviceGetCountry(var communicateFromEntityToInteractor : CommunicateFromEntityToInteractor){

    fun getCountries() {
        val apiService = ApiClient.retofitClient!!.create(ApiInterface::class.java)
        val call: Call<List<Country>> = apiService.getCountries()

        call.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                Log.e("response", response.toString())
                var list = Mapper ().changeToCountryPojo(response.body())
                communicateFromEntityToInteractor.setResultWhenSucess(list)

            }
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Log.e("error", t.toString())
                communicateFromEntityToInteractor.setResultWhenFailed(t.toString())
            }
        })
    }
}