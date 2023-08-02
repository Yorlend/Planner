package com.example.planner.data.entities.mappers

import com.example.planner.data.entities.TodoEntity
import com.example.planner.domain.models.TodoModel

object TodoMapper {
    fun fromModel(model: TodoModel): TodoEntity {
        return TodoEntity(
            id = model.id,
            todo = model.todo,
            completed = model.completed,
            user = model.user,
        )
    }

    fun toModel(entity: TodoEntity): TodoModel {
        return TodoModel(
            id = entity.id,
            todo = entity.todo,
            completed = entity.completed,
            user = entity.user,
        )
    }
}