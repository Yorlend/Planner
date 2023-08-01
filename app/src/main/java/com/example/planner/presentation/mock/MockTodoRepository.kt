package com.example.planner.presentation.mock

import com.example.planner.domain.models.TodoModel
import com.example.planner.domain.repositories.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.concurrent.atomic.AtomicLong

class MockTodoRepository : TodoRepository {
    private val todos = mutableListOf<TodoModel>(
        TodoModel("AAAAA", 1, id = 1),
        TodoModel("BBBBB", 1, id = 2),
        TodoModel("CCCCCCCCCCC", 1, completed = true, id = 3),
        TodoModel("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDdd", 1, id = 4),
        TodoModel(
            "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEe",
            1,
            id = 5,
        ),
        TodoModel("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF", 1, id = 6),
        TodoModel("WWWWWWW", 1, id = 7),
        TodoModel("AAEAAA", 1, id = 8),
    )
    private val count: AtomicLong = AtomicLong(9)

    override fun getFlow(limit: Int, skip: Int): Flow<List<TodoModel>> {
        return flowOf(
            todos
                .take(if (limit == 0) todos.size else limit)
                .drop(skip)
        )
    }

    override suspend fun get(limit: Int, skip: Int): List<TodoModel> {
        return todos
            .take(if (limit == 0) todos.size else limit)
            .drop(skip)
            .toMutableList()
    }

    override suspend fun get(id: Int): TodoModel {
        return try {
            todos[id]
        } catch (exception: Exception) {
            return TodoModel("Todo Not Found", 0)
        }
    }

    override suspend fun getRandom(): TodoModel {
        return todos.random()
    }

    override fun getFlowForUser(userId: Int, limit: Int, skip: Int): Flow<List<TodoModel>> {
        return flowOf(
            todos.filter {
                it.user == userId
            }
                .take(if (limit == 0) todos.size else limit)
                .drop(skip)
        )
    }

    override suspend fun getForUser(userId: Int, limit: Int, skip: Int): List<TodoModel> {
        return todos.filter {
            it.user == userId
        }
                    .take(if (limit == 0) todos.size else limit)
                    .drop(skip)
    }

    override suspend fun add(todoModel: TodoModel): TodoModel {
        val todo = TodoModel(
            id = count.getAndIncrement().toInt(),
            todo = todoModel.todo,
            user = todoModel.user,
            completed = todoModel.completed,
            isDeleted = todoModel.isDeleted
        )
        todos.add(todo)
        return todo
    }

    override suspend fun update(todoModel: TodoModel) {
        val todo = todos.find {
            it.id == todoModel.id
        }
        todo?.let {
            val index = todos.indexOf(todo)
            todos.set(index, todoModel)
        }
    }

    override suspend fun delete(id: Int): TodoModel {
        val todo = todos.find {
            it.id == id
        }
        todo?.let {
            return TodoModel(
                todo = todo.todo,
                user = todo.user,
                id = todo.id,
                isDeleted = true,
                completed = todo.completed,
                )
        }
        return TodoModel(
            "NotFound",
            0,
        )
    }

}