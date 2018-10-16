package com.example.georgeissac.mvp.room

import android.arch.persistence.room.*
import com.example.georgeissac.mvp.retrofit.response.Country
import android.arch.lifecycle.LiveData



@Dao
interface CountryDao{

    @Query("SELECT * FROM country")
    fun getAll(): List<Country>

    @Insert
    fun insert(list: List<Country>)

    @Query("SELECT * FROM country WHERE name LIKE :name")
    fun getSearchList(name: String): LiveData<List<Country>>

    @Query("SELECT * FROM country WHERE name = :name")
    fun getIfExist(name: String): List<Country>


    /*@Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)*/

}