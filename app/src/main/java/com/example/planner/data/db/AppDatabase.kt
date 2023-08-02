package com.example.planner.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.planner.data.dao.TodoDAO
import com.example.planner.data.dao.UserDAO
import com.example.planner.data.entities.TodoEntity
import com.example.planner.data.entities.UserEntity

@Database(entities = [TodoEntity::class, UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDAO(): TodoDAO

    abstract fun userDAO(): UserDAO
}