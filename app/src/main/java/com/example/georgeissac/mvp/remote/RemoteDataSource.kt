package com.example.georgeissac.mvp.remote

import android.util.Log
import com.example.georgeissac.mvp.data.RemoteDataSourceInterface
import com.example.georgeissac.mvp.domain.countryUseCase.response.Country
import com.example.georgeissac.mvp.retrofit.ApiInterface
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(val apiInterface : ApiInterface){

    fun getCountries(remoteDataSourceInterface : RemoteDataSourceInterface) {
        val call: Observable<List<Country>> = apiInterface.getCountries()
        /*call.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                Log.e("response", response.toString())

                remoteDataSourceInterface.setResultWhenSuccess(response.body())
            }
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Log.e("error", t.toString())
                remoteDataSourceInterface.setResultWhenFailed(t.toString())
            }
        })*/
    }

}