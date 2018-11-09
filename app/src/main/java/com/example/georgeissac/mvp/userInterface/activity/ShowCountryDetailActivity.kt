package com.example.georgeissac.mvp.userInterface.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.georgeissac.mvp.MyApp
import com.example.georgeissac.mvp.R
import com.example.georgeissac.mvp.util.Constants
import kotlinx.android.synthetic.main.show_activity.*
import com.example.georgeissac.mvp.util.Utilities
import javax.inject.Inject


class ShowCountryDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var utilities: Utilities

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_activity)

        (application as MyApp).appComponent.inject(this)

        val intent = intent
        val countryName = intent.getStringExtra(Constants.countryName)
        val countryImg = intent.getStringExtra(Constants.countryImg)

        utilities.setImage(countryImg, imageView)
        countryNameToShow.text = countryName
    }

}