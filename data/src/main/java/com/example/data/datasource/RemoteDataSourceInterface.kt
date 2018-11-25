package com.example.data.datasource

import com.example.georgeissac.mvp.domain.countryUseCase.response.Country

interface RemoteDataSourceInterface {
    fun setResultWhenSuccess(list: List<Country>)
    fun setResultWhenFailed(error: String)
}