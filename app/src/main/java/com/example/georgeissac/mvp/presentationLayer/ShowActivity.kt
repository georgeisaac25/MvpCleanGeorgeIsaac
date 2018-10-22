package com.example.georgeissac.mvp.presentationLayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.georgeissac.mvp.R
import kotlinx.android.synthetic.main.show_activity.*
import com.bumptech.glide.load.resource.file.FileToStreamDecoder
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.StreamEncoder
import com.caverock.androidsvg.SVG
import java.io.InputStream
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.georgeissac.mvp.presentationLayer.svg.SvgDecoder
import com.example.georgeissac.mvp.presentationLayer.svg.SvgDrawableTranscoder
import com.example.georgeissac.mvp.presentationLayer.svg.SvgSoftwareLayerSetter


class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_activity)

        val intent = intent
        var countryName = intent.getStringExtra("countryName")
        var countryImg = intent.getStringExtra("countryImg")

         var requestBuilder= Glide.with(this)
                .using(Glide.buildStreamModelLoader(Uri::class.java, this), InputStream::class.java)
                .from(Uri::class.java)
                .`as`(SVG::class.java)
                .transcode(SvgDrawableTranscoder(), PictureDrawable::class.java)
                .sourceEncoder(StreamEncoder())
                .cacheDecoder(FileToStreamDecoder<SVG>(SvgDecoder()))
                .decoder(SvgDecoder())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .animate(android.R.anim.fade_in)
                .listener(SvgSoftwareLayerSetter<Uri>())

        val uri = Uri.parse(countryImg)
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                // SVG cannot be serialized so it's not worth to cache it
                .load(uri)
                .into(imageView)

        countryNameToShow.setText(countryName)


        //Log.e("name",country.name)
    }

}