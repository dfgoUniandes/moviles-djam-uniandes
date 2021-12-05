package com.example.vinilosdjam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vinilosdjam.models.Album
import com.example.vinilosdjam.models.Artist
import com.example.vinilosdjam.models.User

@Database(entities = [Album::class, Artist::class, User::class], version = 1, exportSchema = false)
abstract class VinilosRoomDatabase: RoomDatabase() {
    abstract fun albumDao(): AlbumDao
    abstract fun artistDao(): ArtistDao
    abstract fun userDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: VinilosRoomDatabase? = null

        fun getDatabase(context: Context): VinilosRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VinilosRoomDatabase::class.java,
                    "vinilos_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}