package com.example.georgeissac.mvp.presentation.presenter

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter(){
    val disposable = CompositeDisposable()
}