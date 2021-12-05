package com.example.vinilosdjam.repositories


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.android.volley.VolleyError
import com.example.vinilosdjam.database.AlbumDao
import com.example.vinilosdjam.models.Album
import com.example.vinilosdjam.network.NetworkServiceAdapter

class AlbumRepository (val application: Application, private val albumDao: AlbumDao){
    suspend fun refreshData(): List<Album> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
//        return NetworkServiceAdapter.getInstance(application).getAlbums()
        var cached = albumDao.getAlbums()
        return if(cached.isNullOrEmpty()){
            val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if( cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE){
                emptyList()
            } else NetworkServiceAdapter.getInstance(application).getAlbums()
        } else cached
    }
}