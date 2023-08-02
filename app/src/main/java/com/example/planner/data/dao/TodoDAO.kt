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

    @Query("SELECT * FROM todos LIMIT :limit OFFSET :offset")
    fun getAllFlow(limit: Int, offset: Int = 0): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todos LIMIT :limit OFFSET :offset")
    suspend fun getAll(limit: Int, offset: Int = 0): List<TodoEntity>

    @Query("SELECT * FROM todos WHERE user_id = :userId LIMIT :limit OFFSET :offset")
    fun getFlowForUser(userId: Int, limit: Int, offset: Int = 0): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todos WHERE user_id = :userId LIMIT :limit OFFSET :offset")
    suspend fun getForUser(userId: Int, limit: Int, offset: Int = 0): List<TodoEntity>

    @Insert
    suspend fun insert(todo: TodoEntity): Long

    @Update
    suspend fun update(todo: TodoEntity)

    @Delete
    suspend fun delete(todoEntity: TodoEntity)
}