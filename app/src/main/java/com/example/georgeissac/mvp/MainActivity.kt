package com.example.georgeissac.mvp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.georgeissac.mvp.retrofit.ApiClient
import com.example.georgeissac.mvp.retrofit.ApiInterface
import com.example.georgeissac.mvp.retrofit.response.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.georgeissac.mvp.adapter.CountryAdapter


class MainActivity : AppCompatActivity() {
    lateinit var recyclerView : RecyclerView
    lateinit var countryAdapter: CountryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView


        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setLayoutManager(mLayoutManager)
        recyclerView.setItemAnimator(DefaultItemAnimator())



        val apiService = ApiClient.retofitClient!!.create(ApiInterface::class.java)
        val call : Call<List<Country>> = apiService.getCountries();

        call.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                Log.e("response",   response.toString())

                countryAdapter = CountryAdapter(response.body())
                recyclerView.setAdapter(countryAdapter)


            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                // Log error here since request failed
                Log.e("error", t.toString())
            }
        })


    }
}
