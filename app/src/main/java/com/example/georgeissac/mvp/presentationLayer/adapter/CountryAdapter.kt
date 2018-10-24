package com.example.georgeissac.mvp.presentationLayer.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.georgeissac.mvp.R
import com.example.georgeissac.mvp.presentationLayer.interfaces.PassPositionOfItemClicked
import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country
import com.example.georgeissac.mvp.util.Utilities


class CountryAdapter constructor(var listCountry : List<Country>,  clicked: PassPositionOfItemClicked,var utilities: Utilities)
    : RecyclerView.Adapter<CountryAdapter.MyViewHolder>() {

    private var positionClicked: PassPositionOfItemClicked = clicked


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var countryName : TextView = itemView.findViewById (R.id.countryName)
        var image : ImageView = itemView.findViewById (R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.single_row_country, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val country : Country = listCountry.get(position)
        holder.countryName.setText(country.name)

        utilities.setImage(country.flag,holder.image)

        holder.itemView.setOnClickListener {
            positionClicked.getPositionOfItemForSingleTapUpClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return listCountry.size
    }
}