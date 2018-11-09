package com.example.georgeissac.mvp.domain.searchCountryUseCase

import com.example.georgeissac.mvp.database.CountryEntity//problem
import com.example.georgeissac.mvp.domain.CountryPojo
import com.example.georgeissac.mvp.domain.interfaces.RepositoryInterfaceContract
import com.example.georgeissac.mvp.domain.searchCountryUseCase.request.Request
import io.reactivex.Maybe

class SearchCountryUseCase(var repositoryInterfaceContract: RepositoryInterfaceContract){
    fun getCountry(searchString: String) : Maybe<List<CountryPojo>> {
        val request = Request(searchString)
        return repositoryInterfaceContract.searchCountryInDb(request)
    }
}