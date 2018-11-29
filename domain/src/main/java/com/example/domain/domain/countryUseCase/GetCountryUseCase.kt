package com.example.domain.domain.countryUseCase

import com.example.domain.domain.countryUseCase.interfaces.RepositoryInterface
import com.example.domain.domain.countryUseCase.interfaces.UseCaseInterface
import com.example.domain.domain.CountryPojo
import com.example.domain.domain.interfaces.RepositoryContract
import io.reactivex.Maybe

class GetCountryUseCase(var repositoryContract: RepositoryContract)
    : RepositoryInterface {

    lateinit var communicateWithModelFromInteractor: UseCaseInterface;
    // Request
    /*fun getCountry(communicateWithModelFromInteractor: UseCaseInterface) {
        this.communicateWithModelFromInteractor = communicateWithModelFromInteractor
        repositoryContract.getCountries(this)
    }*/

    fun getCountryUsingRx (): Maybe<List<CountryPojo>> {
        return repositoryContract.getCountryRx()
    }

    // Response
    override fun setResultWhenSucess(result: List<CountryPojo>) {
        communicateWithModelFromInteractor.setResultWhenSucess(result)
    }

    override fun setResultWhenFailed(resultWhenFailed: String) {
        communicateWithModelFromInteractor.setResultWhenFailed(resultWhenFailed)
    }
}