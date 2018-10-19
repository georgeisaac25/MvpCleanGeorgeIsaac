package com.example.georgeissac.mvp.presenter

import android.content.Context;
import android.support.v4.content.Loader;

import com.example.georgeissac.mvp.presentationLayer.CountryPresenter


class GetPresenter<T>(context: Context, var presenterType: String) : Loader<T>(context) {

    private var presenter: T? = null

    override fun onStartLoading() {
        //init loader on start loading called for 1st time
        super.onStartLoading();

        if (presenter != null) {
            deliverResult(presenter)
            return;
        }

        // Otherwise, force a load
        forceLoad();
    }

    override fun onForceLoad() {
        // TODO Auto-generated method stub
        super.onForceLoad()
        presenter = getPresenterFromFactory(presenterType)
        deliverResult(presenter)

    }




    private fun getPresenterFromFactory(presenterType: String): T? {
        /*step 5*/
        return if (presenterType == "MyViewPresenter") {
            return MyPresenter() as T
        }
        else if(presenterType == "CountryPresenter"){
            return CountryPresenter() as T
        }

        else null
    }


}
