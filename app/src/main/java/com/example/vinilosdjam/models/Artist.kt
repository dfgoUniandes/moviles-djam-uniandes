package com.example.vinilosdjam.models

import org.json.JSONArray

data class Artist(
    val id:Int,
    val name:String,
    val image:String,
    val description:String,
    val birthDate:String,
    val albums: JSONArray,
    val performerPrizes: JSONArray
)
