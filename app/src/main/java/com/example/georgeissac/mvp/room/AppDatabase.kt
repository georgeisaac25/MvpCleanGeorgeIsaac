package com.example.georgeissac.mvp.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.georgeissac.mvp.usecase.getCountry.response.Country

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