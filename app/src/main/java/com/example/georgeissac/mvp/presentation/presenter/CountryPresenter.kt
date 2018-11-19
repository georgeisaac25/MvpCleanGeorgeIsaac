package com.example.georgeissac.mvp.presentation.presenter

import com.example.georgeissac.mvp.presentation.interfaces.CountryContract
import com.example.georgeissac.mvp.domain.CountryPojo
import com.example.georgeissac.mvp.domain.countryUseCase.GetCountryUseCase
import com.example.georgeissac.mvp.domain.interfaces.UseCaseContractInterface
import com.example.georgeissac.mvp.domain.searchCountryUseCase.SearchCountryUseCase
import com.example.georgeissac.mvp.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers

class CountryPresenter(
    var view: CountryContract.view?,
    var searchCountryUseCase: SearchCountryUseCase,
    var getCountry: GetCountryUseCase
    ) :
    com.example.georgeissac.mvp.domain.countryUseCase.interfaces.UseCaseInterface,
    UseCaseContractInterface {

    private val disposable = CompositeDisposable()

    override fun setResultWhenSucess(result: List<CountryPojo>?) {
        view?.showList(result)
    }

    override fun setResultWhenFailed(resultWhenFailed: String) {
        view?.showError(resultWhenFailed)
    }

    fun searchInDb(textToSearch: String) {
        var searchText = textToSearch
        searchText = "%$searchText%"
        searchCountry(searchText)
    }

    fun selectedCountry(pos: Int, list: List<CountryPojo>) {
        val country = list.get(pos)
        view?.navigateToShowCountryDetailActivity(country)
    }

    override fun getCountyList() {
        getCountry.getCountry(this)
    }

    override fun searchCountry(string: String) {
        disposable.add(
            searchCountryUseCase.getCountry(searchString = string).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableMaybeObserver<List<CountryPojo>>() {
                    override fun onSuccess(list: List<CountryPojo>) {
                        if (list.isNotEmpty()) {
                            view?.showList(list)
                        }
                    }

                    override fun onComplete() {
                        view?.showError(Constants.noList)
                    }

                    override fun onError(e: Throwable) {
                        view?.showError(Constants.tryAgain)
                    }
                })
        )
    }

    fun onStop() {
        disposable.clear()
        view = null
    }

}