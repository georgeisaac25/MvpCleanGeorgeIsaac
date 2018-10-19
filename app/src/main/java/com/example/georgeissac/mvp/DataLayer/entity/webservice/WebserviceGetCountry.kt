package com.example.georgeissac.mvp.DataLayer.entity.webservice

import android.app.Application
import android.util.Log
import com.example.georgeissac.mvp.MyApp
import com.example.georgeissac.mvp.retrofit.ApiClient
import com.example.georgeissac.mvp.retrofit.ApiInterface
import com.example.georgeissac.mvp.usecase.getCountry.repository.CommunicateFromEntityToInteractor
import com.example.georgeissac.mvp.usecase.getCountry.response.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class WebserviceGetCountry(var communicateFromEntityToInteractor : CommunicateFromEntityToInteractor){
                           //,@Inject var apiService: ApiInterface){

    //fun passInterface()



    fun getCountries() {


        val apiService = ApiClient.retofitClient!!.create(ApiInterface::class.java)
        val call: Call<List<Country>> = apiService.getCountries()

        call.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                Log.e("response", response.toString())
                communicateFromEntityToInteractor.setResultWhenSucess(response.body())

            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Log.e("error", t.toString())
                communicateFromEntityToInteractor.setResultWhenFailed(t.toString())
            }
        })
    }
}