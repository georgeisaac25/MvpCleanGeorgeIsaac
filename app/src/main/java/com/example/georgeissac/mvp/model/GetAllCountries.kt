package com.example.georgeissac.mvp.model

import android.util.Log
import com.example.georgeissac.mvp.retrofit.ApiClient
import com.example.georgeissac.mvp.retrofit.ApiInterface
import com.example.georgeissac.mvp.retrofit.response.Country
import com.example.georgeissac.mvp.room.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAllCountries(var communicateWithModelFromPresenter: CommunicateWithModelFromPresenter) {

    fun getCountries() {
        val apiService = ApiClient.retofitClient!!.create(ApiInterface::class.java)
        val call: Call<List<Country>> = apiService.getCountries();

        call.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                Log.e("response", response.toString())
                communicateWithModelFromPresenter.setResultWhenSucess(response.body())

            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Log.e("error", t.toString())
                communicateWithModelFromPresenter.setResultWhenFailed(t.toString())
            }
        })
    }


}