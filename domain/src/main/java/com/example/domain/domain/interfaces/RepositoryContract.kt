package com.example.domain.domain.interfaces

import com.example.domain.domain.countryUseCase.interfaces.RepositoryInterface
import com.example.domain.domain.CountryPojo
import com.example.georgeissac.mvp.domain.searchCountryUseCase.request.Request
import io.reactivex.Maybe

//Using dependency inversion
interface RepositoryContract {
    fun searchCountryInDb(request: Request): Maybe<List<CountryPojo>>
    fun getCountries(repositoryInterface: RepositoryInterface)
    fun getCountryRx(): Maybe<List<CountryPojo>>
}
