package com.example.vinilosdjam.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilosdjam.models.User
import com.example.vinilosdjam.network.NetworkServiceAdapter


class UserRepository (val application: Application){
    fun refreshData(callback: (List<User>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getUser({
            callback(it)
        },
            onError
        )
    }
}