package com.example.planner.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.planner.data.entities.TodoEntity
import com.example.planner.data.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDAO {

    @Query("SELECT * FROM todos")
    fun getAllFlow(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todos")
    suspend fun getAll(): List<TodoEntity>

    @Query("SELECT * FROM todos WHERE user_id = :userId")
    fun getFlowForUser(userId: Int): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todos WHERE user_id = :userId")
    suspend fun getForUser(userId: Int): List<TodoEntity>

    @Insert
    suspend fun insert(todo: TodoEntity): Long

    @Update
    suspend fun update(todo: TodoEntity)

    @Delete
    suspend fun delete(todoEntity: TodoEntity)
}