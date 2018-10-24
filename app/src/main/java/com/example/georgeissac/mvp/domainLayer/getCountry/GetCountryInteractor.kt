package com.example.georgeissac.mvp.domainLayer.getCountry

import com.example.georgeissac.mvp.dataLayer.webservice.WebserviceGetCountry
import com.example.georgeissac.mvp.domainLayer.getCountry.repository.CommunicateFromEntityToInteractor
import com.example.georgeissac.mvp.domainLayer.getCountry.repository.CommunicateWithPresenterFromInteractor
import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country

class GetCountryInteractor(var communicateWithModelFromInteractor: CommunicateWithPresenterFromInteractor)
    : CommunicateFromEntityToInteractor {
    // Request
    fun callWebServiceOrDb() {
        var webserviceGetCountry = WebserviceGetCountry(this)
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