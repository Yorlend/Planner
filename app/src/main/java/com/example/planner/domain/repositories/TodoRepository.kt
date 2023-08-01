package com.example.planner.domain.repositories

import com.example.planner.domain.models.TodoModel
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getFlow(limit: Int, skip: Int): Flow<List<TodoModel>>
    suspend fun get(limit: Int, skip: Int): List<TodoModel>

    suspend fun get(id: Int): TodoModel

    suspend fun getRandom(): TodoModel

    fun getFlowForUser(userId: Int, limit: Int, skip: Int): Flow<List<TodoModel>>
    suspend fun getForUser(userId: Int, limit: Int, skip: Int): List<TodoModel>

    suspend fun add(todoModel: TodoModel): TodoModel

    suspend fun update(todoModel: TodoModel)

    suspend fun delete(id: Int): TodoModel
}
