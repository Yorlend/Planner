package com.example.planner.data.repositories

import com.example.planner.data.db.AppDatabase
import com.example.planner.data.db.SessionManager
import com.example.planner.data.entities.UserEntity
import com.example.planner.data.entities.mappers.TodoMapper
import com.example.planner.data.entities.mappers.UserMapper
import com.example.planner.domain.models.UserModel
import com.example.planner.domain.repositories.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val sessionManager: SessionManager,
) : AuthRepository {

    override suspend fun getCurrentUser(): UserModel = withContext(Dispatchers.IO) {
        val id = sessionManager.getSession()

        UserMapper.toModel(db.userDAO().get(id))
    }

    override suspend fun logout() = withContext(Dispatchers.IO) {
        sessionManager.logoutFromSession()
    }

    override suspend fun login(login: String, password: String): UserModel =
        withContext(Dispatchers.IO) {
            val user = db.userDAO().login(login, password)

            if (user != null) {
                sessionManager.saveSession(user)
                UserMapper.toModel(user)
            } else {
                throw Exception("user not found")
            }
        }

    override suspend fun register(login: String, password: String): UserModel =
        withContext(Dispatchers.IO) {
            val user = UserEntity(login, password)
            val id = db.userDAO().register(user)

            UserModel(
                user.login,
                user.password,
                id.toInt(),
            )
        }
}