package com.example.georgeissac.mvp.domain.countryUseCase

import com.example.georgeissac.mvp.data.DataRepository
import com.example.georgeissac.mvp.database.CountryPojo
import com.example.georgeissac.mvp.domain.countryUseCase.interfaces.RepositoryInterface
import com.example.georgeissac.mvp.domain.countryUseCase.interfaces.UseCaseInterface

class GetCountryUseCase(var dataRepository: DataRepository)
    : RepositoryInterface {

    lateinit var communicateWithModelFromInteractor: UseCaseInterface;
    // Request
    fun callWebService(communicateWithModelFromInteractor: UseCaseInterface) {
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