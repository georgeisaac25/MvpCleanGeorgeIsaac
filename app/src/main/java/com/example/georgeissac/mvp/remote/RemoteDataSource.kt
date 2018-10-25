package com.example.georgeissac.mvp.remote

import android.util.Log
import com.example.georgeissac.mvp.data.CommunicateFromRemoteToRepository
import com.example.georgeissac.mvp.domain.countryUseCase.response.Country
import com.example.georgeissac.mvp.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(val apiInterface : ApiInterface){

    fun getCountries(communicateFromRemoteToRepository : CommunicateFromRemoteToRepository) {
        val call: Call<List<Country>> = apiInterface.getCountries()
        call.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                Log.e("response", response.toString())

                communicateFromRemoteToRepository.setResultWhenSuccess(response.body())
            }
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Log.e("error", t.toString())
                communicateFromRemoteToRepository.setResultWhenFailed(t.toString())
            }
        })
    }

}