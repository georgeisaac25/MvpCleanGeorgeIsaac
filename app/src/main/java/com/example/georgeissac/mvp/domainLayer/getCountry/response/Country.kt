package com.example.georgeissac.mvp.domainLayer.getCountry.response

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "country", indices = arrayOf(Index(value = ["name"], unique = true)))
class Country /*implements Parcelable */ {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "did")
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "alpha2Code")
    var alpha2Code: String? = null


    @ColumnInfo(name = "alpha3Code")
    var alpha3Code: String? = null

    @ColumnInfo(name = "flag")
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
