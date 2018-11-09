package com.example.georgeissac.mvp.domain.countryUseCase

import com.example.georgeissac.mvp.domain.CountryPojo
import com.example.georgeissac.mvp.domain.countryUseCase.interfaces.RepositoryInterface
import com.example.georgeissac.mvp.domain.countryUseCase.interfaces.UseCaseInterface
import com.example.georgeissac.mvp.domain.interfaces.RepositoryInterfaceContract

class GetCountryUseCase(var repositoryInterfaceContract: RepositoryInterfaceContract)
    : RepositoryInterface {

    lateinit var communicateWithModelFromInteractor: UseCaseInterface;
    // Request
    fun getCountry(communicateWithModelFromInteractor: UseCaseInterface) {
        this.communicateWithModelFromInteractor = communicateWithModelFromInteractor
        repositoryInterfaceContract.getCountries(this)
    }

    // Response
    override fun setResultWhenSucess(result: List<CountryPojo>) {
        communicateWithModelFromInteractor.setResultWhenSucess(result)
    }

    override fun setResultWhenFailed(resultWhenFailed: String) {
        communicateWithModelFromInteractor.setResultWhenFailed(resultWhenFailed)
    }
}