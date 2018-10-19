package com.example.georgeissac.mvp.room

import android.arch.persistence.room.*
import android.arch.lifecycle.LiveData
import com.example.georgeissac.mvp.usecase.getCountry.response.Country


@Dao
interface CountryDao{

    @Query("SELECT * FROM country")
    fun getAll(): List<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<Country>)

    @Query("SELECT * FROM country WHERE name LIKE :name")
    fun getSearchList(name: String): LiveData<List<Country>>

    @Query("SELECT * FROM country WHERE name = :name")
    fun getIfExist(name: String): List<Country>

}