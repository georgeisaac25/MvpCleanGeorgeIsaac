package com.example.georgeissac.mvp.domain.searchCountryUseCase

import com.example.georgeissac.mvp.data.DataRepository
import com.example.georgeissac.mvp.database.CountryPojo
import com.example.georgeissac.mvp.domain.searchCountryUseCase.request.Request
import io.reactivex.Maybe

class SearchCountryUseCase(var dataRepository: DataRepository){
    fun getCountry(searchString: String) : Maybe<List<CountryPojo>> {
        val request = Request(searchString)
        return dataRepository.searchCountryInDb(request)
    }
}