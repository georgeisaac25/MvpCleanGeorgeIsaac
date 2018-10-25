package com.example.georgeissac.mvp.domain.countryUseCase

import com.example.georgeissac.mvp.data.DataRepository
import com.example.georgeissac.mvp.database.CountryPojo
import com.example.georgeissac.mvp.domain.countryUseCase.repository.CommunicateFromEntityToInteractor
import com.example.georgeissac.mvp.domain.countryUseCase.repository.CommunicateWithPresenterFromInteractor

class GetCountryUseCase(var dataRepository: DataRepository)
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