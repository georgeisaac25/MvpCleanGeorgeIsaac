package com.example.georgeissac.mvp.domainLayer.getCountry

import com.example.georgeissac.mvp.dataLayer.webservice.WebserviceGetCountry
import com.example.georgeissac.mvp.domainLayer.getCountry.repository.CommunicateFromEntityToInteractor
import com.example.georgeissac.mvp.domainLayer.getCountry.repository.CommunicateWithPresenterFromInteractor

class GetCountryInteractor(var communicateWithModelFromInteractor: CommunicateWithPresenterFromInteractor)
    : CommunicateFromEntityToInteractor {
    // Request
    fun callWebServiceOrDb() {
        var webserviceGetCountry = WebserviceGetCountry(this)
        webserviceGetCountry.getCountries()
    }

    // Response
    override fun setResultWhenSucess(result: List<CountryPojo>) {
        communicateWithModelFromInteractor.setResultWhenSucess(result)
    }

    override fun setResultWhenFailed(resultWhenFailed: String) {
        communicateWithModelFromInteractor.setResultWhenFailed(resultWhenFailed)
    }
}