package com.example.vinilosdjam.models
import org.json.JSONArray

data class User(
    val id:Int,
    val name:String,
    val telephone:String,
    val email:String,
    val comments: JSONArray,
    val favoritePerformers: JSONArray,
    val collectorAlbums: JSONArray
)