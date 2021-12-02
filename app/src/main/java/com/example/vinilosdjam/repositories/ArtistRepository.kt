package com.example.vinilosdjam.repositories


import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilosdjam.models.Artist
import com.example.vinilosdjam.network.NetworkServiceAdapter

class ArtistRepository (val application: Application){
    fun refreshData(callback: (List<Artist>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getArtist({
            callback(it)
        },
            onError
        )
    }
}