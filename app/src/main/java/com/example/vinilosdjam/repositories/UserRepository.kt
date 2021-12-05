package com.example.vinilosdjam.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.vinilosdjam.database.UserDao
import com.example.vinilosdjam.models.User
import com.example.vinilosdjam.network.NetworkServiceAdapter


//class UserRepository (val application: Application){
//    suspend fun refreshData(): List<User> {
//        return NetworkServiceAdapter.getInstance(application).getUser()
//    }
//}

class UserRepository (val application: Application, private val userDao: UserDao){
    suspend fun refreshData(): List<User> {
        var cached = userDao.getUser()
        return if(cached.isNullOrEmpty()){
            val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if( cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE){
                emptyList()
            } else NetworkServiceAdapter.getInstance(application).getUser()
        } else cached
    }
}