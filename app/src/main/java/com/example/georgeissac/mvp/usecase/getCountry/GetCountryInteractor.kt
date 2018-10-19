package com.example.georgeissac.mvp.usecase.getCountry

import com.example.georgeissac.mvp.DataLayer.entity.webservice.WebserviceGetCountry
import com.example.georgeissac.mvp.usecase.getCountry.repository.CommunicateFromEntityToInteractor
import com.example.georgeissac.mvp.usecase.getCountry.repository.CommunicateWithPresenterFromInteractor
import com.example.georgeissac.mvp.usecase.getCountry.response.Country

class GetCountryInteractor(var communicateWithModelFromInteractors: CommunicateWithPresenterFromInteractor)
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