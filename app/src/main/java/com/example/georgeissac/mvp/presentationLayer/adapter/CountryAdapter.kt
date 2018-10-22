package com.example.georgeissac.mvp.presentationLayer.adapter

import android.content.Context
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


class CountryAdapter constructor(context : Context ,resultList : List<Country>,  clicked: PassPositionOfItemClicked): RecyclerView.Adapter<CountryAdapter.MyViewHolder>() {

    private var listCountry: List<Country>
    private var positionClicked: PassPositionOfItemClicked
    private var c : Context
    init {
        this.listCountry = resultList
        this.positionClicked = clicked
        this.c = context
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var countryName : TextView
        var image : ImageView
        init {
            countryName = itemView.findViewById (R.id.countryName);
            image = itemView.findViewById (R.id.imageView);
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.single_row_country, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val country = listCountry?.get(position)
        holder.countryName.setText(country?.name)

        Utilities().setImage(country.flag,holder.image,context = c)

        holder.itemView.setOnClickListener {
            positionClicked.getPositionOfItemForSingleTapUpClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return listCountry.size
    }
}