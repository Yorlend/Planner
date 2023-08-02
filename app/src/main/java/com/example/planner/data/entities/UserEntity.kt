package com.example.planner.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    val login: String,
    val password: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
)
