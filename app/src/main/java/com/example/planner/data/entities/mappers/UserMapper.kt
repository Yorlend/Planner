package com.example.planner.data.entities.mappers

import com.example.planner.data.entities.UserEntity
import com.example.planner.domain.models.UserModel

object UserMapper {

    fun fromModel(model: UserModel): UserEntity {
        return UserEntity(
            model.login,
            model.password,
            model.id,
        )
    }

    fun toModel(entity: UserEntity): UserModel {
        return UserModel(
            entity.login,
            entity.password,
            entity.id,
        )
    }
}