package com.example.georgeissac.mvp.domain.interfaces

import com.example.georgeissac.mvp.domain.CountryPojo
import com.example.georgeissac.mvp.domain.countryUseCase.interfaces.RepositoryInterface
import com.example.georgeissac.mvp.domain.searchCountryUseCase.request.Request
import io.reactivex.Maybe

interface RepositoryInterfaceContract {
    fun searchCountryInDb(request: Request): Maybe<List<CountryPojo>>
    fun getCountries(repositoryInterface: RepositoryInterface)
}
