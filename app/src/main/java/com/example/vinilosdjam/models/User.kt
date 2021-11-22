package com.example.vinilosdjam.models
import org.json.JSONArray

<<<<<<< HEAD
data class User(
=======
class User (
>>>>>>> 487756573d29041fffe8fc4e621b0380af54fe42
    val id:Int,
    val name:String,
    val telephone:String,
    val email:String,
    val comments: JSONArray,
    val favoritePerformers: JSONArray,
    val collectorAlbums: JSONArray
)