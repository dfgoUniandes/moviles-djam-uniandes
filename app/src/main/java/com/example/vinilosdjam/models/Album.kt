package com.example.vinilosdjam.models

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