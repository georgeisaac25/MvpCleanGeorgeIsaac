package com.example.georgeissac.mvp.domainLayer.getCountry

import com.example.georgeissac.mvp.dataLayer.webservice.WebserviceGetCountry
import com.example.georgeissac.mvp.domainLayer.getCountry.repository.CommunicateFromEntityToInteractor
import com.example.georgeissac.mvp.domainLayer.getCountry.repository.CommunicateWithPresenterFromInteractor
import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country

class GetCountryInteractor(communicateWithModelFromInteractors: CommunicateWithPresenterFromInteractor)
    : CommunicateFromEntityToInteractor{

    private var communicateWithModelFromInteractor: CommunicateWithPresenterFromInteractor

    init {
        this.communicateWithModelFromInteractor = communicateWithModelFromInteractors
    }

    // Request

    fun callWebServiceOrDb(){
        var  webserviceGetCountry = WebserviceGetCountry(this)
        webserviceGetCountry.getCountries()
    }

    // Response
    override fun setResultWhenSucess(result: List<Country>) {
        communicateWithModelFromInteractor.setResultWhenSucess(result)
    }

    override fun setResultWhenFailed(resultWhenFailed: String) {
        communicateWithModelFromInteractor.setResultWhenFailed(resultWhenFailed)
    }


}