package com.example.planner.domain.interactors

import com.example.planner.domain.models.UserModel
import com.example.planner.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend fun getUser(): UserModel = authRepository.getCurrentUser()

    suspend fun logout() = authRepository.logout()

    suspend fun login(username: String, password: String): UserModel =
        authRepository.login(username, password)

    suspend fun register(username: String, password: String): UserModel =
        authRepository.register(username, password)
}
