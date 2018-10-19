package com.example.georgeissac.mvp.presentationLayer

import android.arch.lifecycle.LiveData
import com.example.georgeissac.mvp.presenter.CommunicateWithPresenterFromView
import com.example.georgeissac.mvp.usecase.addCountry.AddCountry
import com.example.georgeissac.mvp.usecase.getCountry.GetCountryInteractor
import com.example.georgeissac.mvp.usecase.getCountry.repository.CommunicateWithPresenterFromInteractor
import com.example.georgeissac.mvp.usecase.getCountry.response.Country
import com.example.georgeissac.mvp.usecase.getCountryOnSearch.SearchCountry
import com.example.georgeissac.mvp.usecase.getCountryOnSearch.request.Request
import com.example.georgeissac.mvp.view.ViewInterface

class CountryPresenter : CommunicateWithPresenterFromInteractor, CommunicateWithPresenterFromView  {


    var view: ViewInterface? = null

    override fun setResultWhenSucess(result: List<Country>?) {
        view?.showList(result)
    }

    override fun setResultWhenFailed(resultWhenFailed: String) {
        view?.showError(resultWhenFailed)
    }

    override fun onViewAttached(view: ViewInterface) {
        this.view = view;
    }

    fun getCountyList(){
        val getAllCountries = GetCountryInteractor(this)
        getAllCountries.callWebServiceOrDb()
    }

    fun searchCountry(string: String, searchCountry: SearchCountry) : LiveData<List<Country>> {

        return searchCountry.getCountry(string)
    }

    fun addCountries(list : List<Country>,addCountry: AddCountry){
        addCountry.addCountries(list)
    }

    override fun onViewDetached() {

    }

    override fun onDestroyed() {

    }

}