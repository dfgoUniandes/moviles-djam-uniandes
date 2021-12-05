package com.example.vinilosdjam.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONArray

@Entity(tableName = "artists_table")
data class Artist(
    @PrimaryKey val id:Int,
    val name:String,
    val image:String,
    val description:String,
    val birthDate:String,
//    val albums: JSONArray,
//    val performerPrizes: JSONArray
)
