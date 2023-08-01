package com.example.planner.domain.repositories

import com.example.planner.domain.models.UserModel

interface AuthRepository {

    suspend fun getCurrentUser(): UserModel

    suspend fun logout()

    suspend fun login(login: String, password: String): UserModel

    suspend fun register(login: String, password: String): UserModel
}
