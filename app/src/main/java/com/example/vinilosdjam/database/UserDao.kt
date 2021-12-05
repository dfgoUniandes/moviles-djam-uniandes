package com.example.vinilosdjam.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vinilosdjam.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users_table")
    fun getUser():List<User>

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(user: User)
//
//    @Query("DELETE FROM users_table")
//    suspend fun deleteAll():Int
}