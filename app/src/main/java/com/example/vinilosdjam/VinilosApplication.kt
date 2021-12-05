package com.example.vinilosdjam

import android.app.Application
import com.example.vinilosdjam.database.VinilosRoomDatabase

class VinylsApplication: Application()  {
    val database by lazy { VinilosRoomDatabase.getDatabase(this) }
}