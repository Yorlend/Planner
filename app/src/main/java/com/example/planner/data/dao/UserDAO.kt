package com.example.planner.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.planner.data.entities.UserEntity

@Dao
interface UserDAO {

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun get(id: Int): UserEntity

    @Query("SELECT * FROM users WHERE login = :username and password = :password")
    suspend fun login(username: String, password: String): UserEntity?

    @Insert
    suspend fun register(user: UserEntity): Long
}