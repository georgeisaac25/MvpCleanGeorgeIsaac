package com.example.georgeissac.mvp.dataLayer.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.georgeissac.mvp.domainLayer.getCountry.CountryPojo
import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country

@Database(entities = arrayOf(CountryPojo::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}