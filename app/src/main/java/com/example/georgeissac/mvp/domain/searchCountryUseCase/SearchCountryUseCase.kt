package com.example.georgeissac.mvp.domain.searchCountryUseCase

import com.example.georgeissac.mvp.domain.CountryPojo
import com.example.georgeissac.mvp.domain.interfaces.RepositoryContract
import com.example.georgeissac.mvp.domain.searchCountryUseCase.request.Request
import io.reactivex.Maybe

class SearchCountryUseCase(var repositoryContract: RepositoryContract){
    fun getCountry(searchString: String) : Maybe<List<CountryPojo>> {
        val request = Request(searchString)
        return repositoryContract.searchCountryInDb(request)
    }
}