package com.example.georgeissac.mvp.presenter

import android.app.Activity
import android.content.Context;
import android.support.v4.content.Loader;
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


class GetPresenter(context: Context, var presenter: MyPresenter) : Loader<MyPresenter>(context) {


    override fun onStartLoading() {
        //init loader on start loading called for 1st time
        super.onStartLoading();

        if (presenter != null) {
            deliverResult(presenter);
            return;
        }

        // Otherwise, force a load
        forceLoad();
    }

    override fun onForceLoad() {
        // TODO Auto-generated method stub
        super.onForceLoad();
        presenter = MyPresenter();
        deliverResult(presenter);

    }

    fun deliverResult(presenter: MyPresenter) {
        // TODO Auto-generated method stub
        super.deliverResult(presenter);
    }


}
