package com.example.vinilosdjam.models
<<<<<<< HEAD

=======
>>>>>>> 487756573d29041fffe8fc4e621b0380af54fe42
import org.json.JSONArray

data class Album(
    val id:Int,
    val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String,
    val performers: JSONArray,
    val tracks: JSONArray,
    val comments: JSONArray
)