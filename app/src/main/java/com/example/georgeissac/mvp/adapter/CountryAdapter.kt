package com.example.georgeissac.mvp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.georgeissac.mvp.R
import com.example.georgeissac.mvp.retrofit.response.Country


class CountryAdapter constructor(var list : List<Country>): RecyclerView.Adapter<CountryAdapter.MyViewHolder>() {

    private var listCountry : List<Country>
    init {
        this.listCountry = list
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var countryName : TextView
        init {
            countryName = itemView.findViewById (R.id.countryName);
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.single_row_country, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val country = listCountry.get(position)
        holder.countryName.setText(country.name)
    }

    override fun getItemCount(): Int {
        return listCountry.size
    }
}