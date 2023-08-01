package com.example.planner.presentation.viewdata

import com.example.planner.domain.models.UserModel

data class UserViewData(
    val login: String,
    val password: String,
    val id: Int? = null,
) {

    companion object {
        fun fromModel(model: UserModel): UserViewData {
            return UserViewData(model.login, model.password, model.id)
        }
    }

    fun toModel(): UserModel {
        return UserModel(login, password, id)
    }
}
