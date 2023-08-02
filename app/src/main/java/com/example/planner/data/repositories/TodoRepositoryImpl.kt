package com.example.planner.data.repositories

import com.example.planner.data.dao.TodoDAO
import com.example.planner.data.db.AppDatabase
import com.example.planner.data.db.SessionManager
import com.example.planner.data.entities.mappers.TodoMapper
import com.example.planner.domain.models.TodoModel
import com.example.planner.domain.repositories.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
) : TodoRepository {
    override fun getFlow(limit: Int, skip: Int): Flow<List<TodoModel>> {
        val todos = db.todoDAO().getAllFlow()

        return todos.map { list ->
            list.map {
                TodoMapper.toModel(it)
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun get(limit: Int, skip: Int): List<TodoModel> = withContext(Dispatchers.IO) {
        val todos = db.todoDAO().getAll()

        todos.map { item ->
            TodoMapper.toModel(item)
        }
    }

    override fun getFlowForUser(userId: Int, limit: Int, skip: Int): Flow<List<TodoModel>> {
        val todos = db.todoDAO().getFlowForUser(userId)

        return todos.map { list ->
            list.map {
                TodoMapper.toModel(it)
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getForUser(userId: Int, limit: Int, skip: Int): List<TodoModel> =
        withContext(Dispatchers.IO) {
            val todos = db.todoDAO().getForUser(userId)

            todos.map { item ->
                TodoMapper.toModel(item)
            }
        }

    override suspend fun add(todoModel: TodoModel): TodoModel = withContext(Dispatchers.IO) {
        val id = db.todoDAO().insert(TodoMapper.fromModel(todoModel))

        TodoModel(
            todoModel.todo,
            todoModel.user,
            todoModel.completed,
            id.toInt(),
            todoModel.isDeleted,
        )
    }

    override suspend fun update(todoModel: TodoModel) = withContext(Dispatchers.IO) {
        db.todoDAO().update(TodoMapper.fromModel(todoModel))
    }

    override suspend fun delete(todoModel: TodoModel) = withContext(Dispatchers.IO) {
        db.todoDAO().delete(TodoMapper.fromModel(todoModel))
    }
}