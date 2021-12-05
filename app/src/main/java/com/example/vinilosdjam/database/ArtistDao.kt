package com.example.vinilosdjam.database

import androidx.room.Dao
import androidx.room.Query
import com.example.vinilosdjam.models.Artist

@Dao
interface ArtistDao {
    @Query("SELECT * FROM artists_table")
    fun getArtist():List<Artist>
}

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(artist: Artist)
//
//    @Query("DELETE FROM artists_table")
//    suspend fun deleteAll():Int
