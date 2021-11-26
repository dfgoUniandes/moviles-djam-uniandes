package com.example.vinilosdjam.repositories


import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilosdjam.models.Album
import com.example.vinilosdjam.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbums({
            callback(it)
        },
            onError
        )
    }
}