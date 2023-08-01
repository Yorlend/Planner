package com.example.planner.presentation.viewdata

import com.example.planner.domain.models.TodoModel

data class TodoViewData(
    val todo: String,
    val user: Int,
    val completed: StatusViewData = StatusViewData.PENDING,
    val id: Int? = null,
) {
    companion object {
        fun fromModel(todoModel: TodoModel): TodoViewData {
            return TodoViewData(
                todo = todoModel.todo,
                completed = StatusViewData.fromModel(todoModel.completed),
                user = todoModel.user,
                id = todoModel.id,
            )
        }
    }

    fun toModel(): TodoModel {
        return TodoModel(
            todo = todo,
            completed = completed.toModel(),
            user = user,
            id = id,
        )
    }
}
