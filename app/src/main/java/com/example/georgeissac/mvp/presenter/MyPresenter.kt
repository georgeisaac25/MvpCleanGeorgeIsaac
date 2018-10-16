package com.example.georgeissac.mvp.presenter

import com.example.georgeissac.mvp.model.CommunicateWithModelFromPresenter
import com.example.georgeissac.mvp.model.GetAllCountries
import com.example.georgeissac.mvp.retrofit.response.Country
import com.example.georgeissac.mvp.view.ViewInterface

class MyPresenter() : CommunicateWithPresenterFromView, CommunicateWithModelFromPresenter, Presenter {


    var view: ViewInterface? = null

    override fun setResultWhenSucess(result: MutableList<Country>?) {
        view?.showList(result);
    }

    override fun setResultWhenFailed(resultWhenFailed: String) {
        view?.showError(resultWhenFailed)
    }

    override fun onViewAttached(view: ViewInterface) {
        this.view = view;
        val getAllCountries = GetAllCountries(this)
        getAllCountries.getCountries()
    }

    override fun onViewDetached() {

    }

    override fun onDestroyed() {

    }

}