package com.example.georgeissac.mvp.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Maybe

@Dao
interface CountryDao {

    @Query("SELECT * FROM countryTable")
    fun getAll(): List<CountryPojo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<CountryPojo>): List<Long>

    @Query("SELECT * FROM countryTable WHERE name LIKE :name")
    fun getSearchList(name: String): LiveData<List<CountryPojo>>

    @Query("SELECT * FROM countryTable WHERE name = :name")
    fun getIfExist(name: String): List<CountryPojo>

    @Query("SELECT * FROM countryTable WHERE name LIKE :name")
    fun getSearchListUsingRx(name: String): Maybe<List<CountryPojo>>

}