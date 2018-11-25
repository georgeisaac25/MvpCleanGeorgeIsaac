package com.example.data.remote

import android.util.Log
import com.example.data.datasource.RemoteDataSourceInterface
import com.example.data.remote.retrofit.ApiInterface
import com.example.georgeissac.mvp.domain.countryUseCase.response.Country
import io.reactivex.Maybe
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call;


class RemoteDataSource(val apiInterface : ApiInterface){

    fun getCountries(remoteDataSourceInterface : RemoteDataSourceInterface) {
        val call: Call<List<Country>> = apiInterface.getCountries()
        call.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                Log.e("response", response.toString())

                remoteDataSourceInterface.setResultWhenSuccess(response.body())
            }
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Log.e("error", t.toString())
                remoteDataSourceInterface.setResultWhenFailed(t.toString())
            }
        })
    }

    fun getCountriesUsingMaybe ()  : Maybe<List<Country>> {
        return apiInterface.getCountriesUsingRx()
    }

}