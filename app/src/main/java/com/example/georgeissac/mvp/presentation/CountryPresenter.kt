package com.example.georgeissac.mvp.presentation

import android.util.Log
import com.example.georgeissac.mvp.presentation.interfaces.CountryContract
import com.example.georgeissac.mvp.domain.addCountryUseCase.AddCountryUseCase
import com.example.georgeissac.mvp.domain.addCountryUseCase.response.ResponseOfAddCountry
import com.example.georgeissac.mvp.database.CountryPojo
import com.example.georgeissac.mvp.domain.countryUseCase.GetCountryUseCase
import com.example.georgeissac.mvp.domain.countryUseCase.repository.CommunicateWithPresenterFromInteractor
import com.example.georgeissac.mvp.domain.searchCountryUseCase.SearchCountryUseCase
import com.example.georgeissac.mvp.domain.interfaces.UseCaseInterface
import com.example.georgeissac.mvp.util.Utilities
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class CountryPresenter(
    var view: CountryContract.view?, var searchCountryUseCase: SearchCountryUseCase, var addCountryUseCase: AddCountryUseCase
    , var getCountry: GetCountryUseCase, var utilities: Utilities
) : CommunicateWithPresenterFromInteractor,
    UseCaseInterface {

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
        getCountry.callWebService(this)
    }

    override fun searchCountry(string: String) {
        disposable.add(searchCountryUseCase.getCountry(searchString = string).subscribeOn(Schedulers.io())
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

            })
        )
    }

    override fun addCountries(list: List<CountryPojo>) {

        disposable.add(addCountryUseCase.addCountries(list).subscribeOn(Schedulers.io())
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
            })
        )
    }

    fun onStop() {
        disposable.clear()
        view = null
    }

}