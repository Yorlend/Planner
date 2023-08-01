package com.example.planner.domain.interactors

import com.example.planner.domain.models.TodoModel
import com.example.planner.domain.repositories.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoInteractor @Inject constructor(
    private val todoRepository: TodoRepository,
) {

    fun get(
        user: Int? = null,
        limit: Int = 0,
        skip: Int = 0,
    ): Flow<List<TodoModel>> {
        user?.let {
            return todoRepository.getFlowForUser(user, limit, skip)
        }
        return todoRepository.getFlow(limit, skip)
    }

    suspend fun add(todoModel: TodoModel): TodoModel = todoRepository.add(todoModel)

    suspend fun update(todoModel: TodoModel) = todoRepository.update(todoModel)

    suspend fun delete(todoModel: TodoModel): TodoModel = todoRepository.delete(todoModel.id!!)
}