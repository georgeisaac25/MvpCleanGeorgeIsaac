package com.example.georgeissac.mvp.domain.countryUseCase.response

class Country {

    var id: Int = 0
    var name: String? = null
    var alpha2Code: String? = null
    var alpha3Code: String? = null
    var flag: String? = null

    override fun toString(): String {
        return "Country{" +
                "name='" + name + '\''.toString() +
                ", alpha2Code='" + alpha2Code + '\''.toString() +
                ", alpha3Code='" + alpha3Code + '\''.toString() +
                ", flag='" + flag + '\''.toString() +
                ", flag='" + flag + '\''.toString() +
                '}'.toString()
    }
}
