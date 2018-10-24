package com.example.georgeissac.mvp.util

import android.content.Context
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.graphics.drawable.PictureDrawable
import android.net.ConnectivityManager
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.StreamEncoder
import com.bumptech.glide.load.resource.file.FileToStreamDecoder
import com.caverock.androidsvg.SVG
import com.example.georgeissac.mvp.R
import com.example.georgeissac.mvp.presentationLayer.svg.SvgDecoder
import com.example.georgeissac.mvp.presentationLayer.svg.SvgDrawableTranscoder
import com.example.georgeissac.mvp.presentationLayer.svg.SvgSoftwareLayerSetter
import io.reactivex.Observable
import kotlinx.android.synthetic.main.show_activity.*
import org.reactivestreams.Subscriber
import java.io.InputStream
import java.util.concurrent.Callable


class Utilities {

    companion object {
        fun isNetAvailable(context: Context) : Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }

    fun setImage(url : String?,imageView: ImageView,context: Context){
        val requestBuilder= Glide.with(context)
                .using(Glide.buildStreamModelLoader(Uri::class.java, context), InputStream::class.java)
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

        val uri = Uri.parse(url)
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                // SVG cannot be serialized so it's not worth to cache it
                .load(uri)
                .override(100, 200)
                .into(imageView)
    }

}