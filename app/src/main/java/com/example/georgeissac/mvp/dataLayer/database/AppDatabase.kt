package com.example.georgeissac.mvp.dataLayer.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao


    /*companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase::class.java, "weather.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }*/
}