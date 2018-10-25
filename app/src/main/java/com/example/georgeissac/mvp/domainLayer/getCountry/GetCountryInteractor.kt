package com.example.georgeissac.mvp.domainLayer.getCountry

import com.example.georgeissac.mvp.dataLayer.DataRepository
import com.example.georgeissac.mvp.domainLayer.getCountry.repository.CommunicateFromEntityToInteractor
import com.example.georgeissac.mvp.domainLayer.getCountry.repository.CommunicateWithPresenterFromInteractor

class GetCountryInteractor(var dataRepository: DataRepository)
    : CommunicateFromEntityToInteractor {

    lateinit var communicateWithModelFromInteractor: CommunicateWithPresenterFromInteractor;
    // Request
    fun callWebService(communicateWithModelFromInteractor: CommunicateWithPresenterFromInteractor) {
        this.communicateWithModelFromInteractor = communicateWithModelFromInteractor
        dataRepository.callWebService(this)
    }

    // Response
    override fun setResultWhenSucess(result: List<CountryPojo>) {
        communicateWithModelFromInteractor.setResultWhenSucess(result)
    }

    override fun setResultWhenFailed(resultWhenFailed: String) {
        communicateWithModelFromInteractor.setResultWhenFailed(resultWhenFailed)
    }
}