package com.example.vinilosdjam.models
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONArray

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey val id:Int,
    val name:String,
    val telephone:String,
    val email:String,
//    val comments: JSONArray,
//    val favoritePerformers: JSONArray,
//    val collectorAlbums: JSONArray
)