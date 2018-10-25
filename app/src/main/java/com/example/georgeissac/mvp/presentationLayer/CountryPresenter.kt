package com.example.georgeissac.mvp.presentationLayer

import android.util.Log
import com.example.georgeissac.mvp.presentationLayer.interfaces.CommunicateWithPresenterFromView
import com.example.georgeissac.mvp.presentationLayer.interfaces.ViewInterface
import com.example.georgeissac.mvp.domainLayer.addCountry.AddCountry
import com.example.georgeissac.mvp.domainLayer.addCountry.ResponseOfAddCountry
import com.example.georgeissac.mvp.domainLayer.getCountry.CountryPojo
import com.example.georgeissac.mvp.domainLayer.getCountry.GetCountryInteractor
import com.example.georgeissac.mvp.domainLayer.getCountry.repository.CommunicateWithPresenterFromInteractor
import com.example.georgeissac.mvp.domainLayer.getCountryOnSearch.SearchCountry
import com.example.georgeissac.mvp.util.Utilities
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class CountryPresenter(var view: ViewInterface?, var searchCountry: SearchCountry, var addCountry: AddCountry, var utilities: Utilities)
    : CommunicateWithPresenterFromInteractor,
        CommunicateWithPresenterFromView, UseCaseInterface {

    private val disposable = CompositeDisposable()

    override fun setResultWhenSucess(result: List<CountryPojo>?) {
        view?.showList(result)
    }

    override fun setResultWhenFailed(resultWhenFailed: String) {
        view?.showError(resultWhenFailed)
    }

    fun getData() {
        if (utilities.isNetAvailable()) {
            view?.callWebService()
        } else {
            searchInDb("")
        }
    }

    fun searchInDb(textToSearch: String) {
        var searchText = textToSearch
        searchText = "%$searchText%"
        view?.callDb(searchText)
    }

    fun getSelectedCountry(pos: Int, list: List<CountryPojo>) {
        val country = list.get(pos)
        view?.navigateToShowCountryDetailActivity(country)
    }

    override fun getCountyList() {
        val getAllCountries = GetCountryInteractor(this)
        getAllCountries.callWebServiceOrDb()
    }

    override fun searchCountry(string: String) {
        disposable.add(searchCountry.getCountry(searchString = string).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableMaybeObserver<List<CountryPojo>>() {
                    override fun onSuccess(list: List<CountryPojo>) {
                        if (list.isNotEmpty()) {
                            view?.showList(list)
                        }
                    }

                    override fun onComplete() {
                        view?.showError("no list found")
                    }

                    override fun onError(e: Throwable) {
                        view?.showError("some error")
                    }

                }))
    }

    override fun addCountries(list: List<CountryPojo>) {

        disposable.add(addCountry.addCountries(list).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ResponseOfAddCountry>() {
                    override fun onComplete() {
                        Log.e("onComplete", "onComplete")
                    }

                    override fun onNext(t: ResponseOfAddCountry) {
                        Log.e("success in", "insertion ${t.getSuccessCount()}")
                    }

                    override fun onError(e: Throwable) {
                        Log.e("error in", "insertion")
                    }
                }))
    }

    override fun onDestroyed() {
        disposable.clear()
        view = null
    }

}