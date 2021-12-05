package com.example.vinilosdjam.network

import android.content.Context
import com.example.vinilosdjam.models.Album

class CacheManager(context: Context) {
    companion object{
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }

}