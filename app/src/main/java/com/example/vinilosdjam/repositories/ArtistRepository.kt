package com.example.vinilosdjam.repositories


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.android.volley.VolleyError
import com.example.vinilosdjam.database.ArtistDao
import com.example.vinilosdjam.models.Album
import com.example.vinilosdjam.models.Artist
import com.example.vinilosdjam.network.NetworkServiceAdapter

//class ArtistRepository (val application: Application){
//    suspend fun refreshData(): List<Artist> {
//        return NetworkServiceAdapter.getInstance(application).getArtist()
//    }
//}

class ArtistRepository (val application: Application, private val artistDao: ArtistDao){
    suspend fun refreshData(): List<Artist>{
        var cached = artistDao.getArtist()
        return if(cached.isNullOrEmpty()){
            val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if( cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE){
                emptyList()
            } else NetworkServiceAdapter.getInstance(application).getArtist()
        } else cached
    }
}