package com.example.vinilosdjam.models
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONArray

@Entity(tableName = "albums_table")
data class Album(
    @PrimaryKey val id:Int,
    val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String,
//    val performers: JSONArray,
//    val tracks: JSONArray,
//    val comments: JSONArray
)