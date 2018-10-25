package com.example.georgeissac.mvp.data

import com.example.georgeissac.mvp.domain.countryUseCase.response.Country

interface CommunicateFromRemoteToRepository {
    fun setResultWhenSuccess(list: List<Country>)
    fun setResultWhenFailed(error: String)
}