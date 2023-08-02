package com.example.planner.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @ColumnInfo(name = "todo") val todo: String,
    @ColumnInfo(name = "completed") val completed: Boolean,
    @ColumnInfo(name = "user_id") val user: Int,
    @PrimaryKey(autoGenerate = true) val id: Int?,
)
