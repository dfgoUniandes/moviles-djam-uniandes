package com.example.vinilosdjam.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vinilosdjam.models.Album


@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums_table")
    fun getAlbums():List<Album>

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(album: Album)
//
//    @Query("DELETE FROM albums_table")
//    suspend fun deleteAll(): Int
}