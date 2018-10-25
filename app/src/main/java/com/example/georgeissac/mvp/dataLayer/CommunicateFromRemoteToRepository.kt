package com.example.georgeissac.mvp.dataLayer

import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country

interface CommunicateFromRemoteToRepository {
    fun setResultWhenSuccess(list: List<Country>)
    fun setResultWhenFailed(error: String)
}