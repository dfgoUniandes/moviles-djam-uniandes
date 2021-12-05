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
    private var albumDetail: HashMap<Int, List<Album>> = hashMapOf()
    fun addComments(albumId: Int, albums: List<Album>){
        if (!albumDetail.containsKey(albumId)){
            albumDetail[albumId] = albums
        }
    }
    fun getComments(albumId: Int) : List<Album>{
        return if (albumDetail.containsKey(albumId)) albumDetail[albumId]!! else listOf<Album>()
    }
}