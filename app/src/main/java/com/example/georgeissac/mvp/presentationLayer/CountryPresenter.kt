package com.example.georgeissac.mvp.presentationLayer

import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.georgeissac.mvp.presentationLayer.interfaces.CommunicateWithPresenterFromView
import com.example.georgeissac.mvp.presentationLayer.interfaces.ViewInterface
import com.example.georgeissac.mvp.domainLayer.addCountry.AddCountry
import com.example.georgeissac.mvp.domainLayer.addCountry.ResponseOfAddCountry
import com.example.georgeissac.mvp.domainLayer.getCountry.GetCountryInteractor
import com.example.georgeissac.mvp.domainLayer.getCountry.repository.CommunicateWithPresenterFromInteractor
import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country
import com.example.georgeissac.mvp.domainLayer.getCountryOnSearch.SearchCountry
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class CountryPresenter : CommunicateWithPresenterFromInteractor,
        CommunicateWithPresenterFromView,UseCaseInterface  {

    var view: ViewInterface? = null
    private val disposable = CompositeDisposable()

    override fun setResultWhenSucess(result: List<Country>?) {
        view?.showList(result)
    }

    override fun setResultWhenFailed(resultWhenFailed: String) {
        view?.showError(resultWhenFailed)
    }

    override fun onViewAttached(view: ViewInterface) {
        this.view = view
    }

    override fun getCountyList(){
        val getAllCountries = GetCountryInteractor(this)
        getAllCountries.callWebServiceOrDb()
    }

    override fun searchCountryUsingLiveData(string: String, searchCountry: SearchCountry) : LiveData<List<Country>> {
        return searchCountry.getCountryUsingLive(string)
    }

    /*The Maybe operates with the following sequential protocol:
    onSubscribe (onSuccess | onError | onComplete)?
    Note that onSuccess, onError and onComplete are mutually exclusive events; unlike Observable, onSuccess is never followed by onError or onComplete.

    When there is no user in the database and the query returns no rows, Maybe will complete.
    When there is a user in the database, Maybe will trigger onSuccess and it will complete.
    https://medium.com/androiddevelopers/room-rxjava-acb0cd4f3757
    */

    override fun searchCountry(searchString: String, searchCountry: SearchCountry) {
        disposable.add(searchCountry.getCountry(searchString = searchString).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableMaybeObserver<List<Country>>(){
                    override fun onSuccess(list: List<Country>) {
                        view?.showList(list)
                    }

                    override fun onComplete() {
                        view?.showError("no list found")
                    }

                    override fun onError(e: Throwable) {
                        view?.showError("some error")
                    }

                }))
    }

    override fun addCountries(list : List<Country>,addCountry: AddCountry){

        disposable.add(addCountry.addCountries(list).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ResponseOfAddCountry>() {
                    override fun onComplete() {
                        Log.e("onComplete","onComplete")
                    }

                    override fun onNext(t: ResponseOfAddCountry) {
                        Log.e("success in","insertion ${t.getSucessCount()}")
                    }

                    override fun onError(e: Throwable) {
                        Log.e("error in","insertion")
                    }
                }))
    }

    override fun onViewDetached() {
        disposable.clear()
    }

    override fun onDestroyed() {

    }

}