package com.example.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Maybe

@Dao
interface CountryDao {

    @Query("SELECT * FROM countryTable")
    fun getAll(): List<CountryEntity>

    @Query("SELECT * FROM countryTable")
    fun getAllUsingRx(): Maybe<List<CountryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<CountryEntity>): List<Long>

    //@Query("SELECT * FROM countryTable WHERE name LIKE :name")
    //fun getSearchList(name: String): LiveData<List<CountryEntity>>

    @Query("SELECT * FROM countryTable WHERE name = :name")
    fun getIfExist(name: String): List<CountryEntity>

    @Query("SELECT * FROM countryTable WHERE name LIKE :name")
    fun getSearchListUsingRx(name: String): Maybe<List<CountryEntity>>

}