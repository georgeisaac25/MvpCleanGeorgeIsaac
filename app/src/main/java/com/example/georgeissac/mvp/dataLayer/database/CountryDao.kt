package com.example.georgeissac.mvp.dataLayer.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country
import io.reactivex.Maybe


@Dao
interface CountryDao {

    @Query("SELECT * FROM country")
    fun getAll(): List<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<Country>): List<Long>

    @Query("SELECT * FROM country WHERE name LIKE :name")
    fun getSearchList(name: String): LiveData<List<Country>>

    @Query("SELECT * FROM country WHERE name = :name")
    fun getIfExist(name: String): List<Country>

    @Query("SELECT * FROM country WHERE name LIKE :name")
    fun getSearchListUsingRx(name: String): Maybe<List<Country>>

}