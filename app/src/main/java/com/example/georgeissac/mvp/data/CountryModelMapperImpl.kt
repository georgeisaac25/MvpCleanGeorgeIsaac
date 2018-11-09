package com.example.georgeissac.mvp.data

import com.example.georgeissac.mvp.database.CountryEntity
import com.example.georgeissac.mvp.domain.CountryPojo
import com.example.georgeissac.mvp.domain.interfaces.CountryModelMapper

class CountryModelMapperImpl : CountryModelMapper<List<CountryEntity>, List<CountryPojo>> {
    override fun fromEntity(fromList: List<CountryEntity>): List<CountryPojo> {
        val list: ArrayList<CountryPojo> = ArrayList()
        for (from in fromList) {
            list.add(
                CountryPojo(from.name, from.alpha2Code, from.alpha3Code, from.flag)
            )
        }
        return list
    }

    override fun toEntity(fromList: List<CountryPojo>): List<CountryEntity> {
        val list: ArrayList<CountryEntity> = ArrayList()
        for (from in fromList) {
            val countryEntity = CountryEntity()
            countryEntity.flag = from.flag
            countryEntity.name = from.name
            countryEntity.alpha3Code = from.alpha3Code
            countryEntity.alpha2Code = from.alpha2Code
            list.add(countryEntity)
        }
        return list
    }

}