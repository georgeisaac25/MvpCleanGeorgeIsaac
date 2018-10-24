package com.example.georgeissac.mvp.presentationLayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.georgeissac.mvp.R
import kotlinx.android.synthetic.main.show_activity.*
import com.example.georgeissac.mvp.util.Utilities


class ShowCountryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_activity)

        val intent = intent
        val countryName = intent.getStringExtra("countryName")
        val countryImg = intent.getStringExtra("countryImg")

        Utilities().setImage(countryImg,imageView,this)
        countryNameToShow.setText(countryName)
    }

}