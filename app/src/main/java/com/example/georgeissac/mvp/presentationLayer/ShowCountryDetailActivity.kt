package com.example.georgeissac.mvp.presentationLayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.georgeissac.mvp.MyApp
import com.example.georgeissac.mvp.R
import kotlinx.android.synthetic.main.show_activity.*
import com.example.georgeissac.mvp.util.Utilities
import javax.inject.Inject


class ShowCountryDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var utilities: Utilities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_activity)

        (application as MyApp).apiComponent.inject(this)

        val intent = intent
        val countryName = intent.getStringExtra("countryName")
        val countryImg = intent.getStringExtra("countryImg")

        utilities.setImage(countryImg,imageView)
        countryNameToShow.text = countryName
    }

}