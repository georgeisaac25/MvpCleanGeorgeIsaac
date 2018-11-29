package com.example.georgeissac.mvp.presentation.presenter

import com.example.georgeissac.mvp.presentation.interfaces.CountryContract
import com.example.domain.domain.CountryPojo
import com.example.domain.domain.countryUseCase.GetCountryUseCase
import com.example.domain.domain.interfaces.UseCaseContractInterface
import com.example.georgeissac.mvp.domain.searchCountryUseCase.SearchCountryUseCase
import com.example.georgeissac.mvp.free.ChooseFlavour
import com.example.georgeissac.mvp.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers

class CountryPresenter(
    var view: CountryContract.view?,
    var searchCountryUseCase: SearchCountryUseCase,
    var getCountry: GetCountryUseCase
    ) : BasePresenter(),
    UseCaseContractInterface {


    fun searchInDb(textToSearch: String) {
        var searchText = textToSearch
        searchText = "%$searchText%"
        searchCountry(searchText)
    }

    fun selectedCountry(pos: Int, list: List<CountryPojo>) {
        val country = list.get(pos)
        view?.navigateToShowCountryDetailActivity(country)
    }

    /* TODO
    override fun getCountyList() {
        getCountry.getCountry(this)
    }*/

    override fun getCountyListUsingRx(){
        disposable.add(
            getCountry.getCountryUsingRx().subscribeOn(Schedulers.io())
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

    fun showHideSearchbox() {
        if(Constants.free == ChooseFlavour.string){
            view?.showFreeVersionToast(Constants.free)
        }
    }

}