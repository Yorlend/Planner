package com.example.planner.presentation.mock

import com.example.planner.domain.models.UserModel
import com.example.planner.domain.repositories.AuthRepository
import java.util.concurrent.atomic.AtomicLong

class MockAuthRepository : AuthRepository {
    private val users = mutableListOf<UserModel>(
        UserModel(
            "admin",
            "admin",
            1
        )
    )
    private val usersCount: AtomicLong = AtomicLong(1)
    private var user: UserModel? = null

    override suspend fun getCurrentUser(): UserModel {
        user?.let {
            return it
        }
        throw Exception("unauthorized")
    }

    override suspend fun logout() {
        user = null
    }

    override suspend fun login(login: String, password: String): UserModel {
        user = users.find {
            it.login == login && it.password == password
        }
        if (user == null) {
            throw Exception("User not found")
        }

        return user as UserModel
    }

    override suspend fun register(login: String, password: String): UserModel {
        val req = users.find {
            it.login == login
        }
        req?.let {
            throw Exception("User is already registered")
        }

        val user = UserModel(
            login = login,
            password = password,
            id = usersCount.incrementAndGet().toInt()
        )

        users.add(user)
        return user
    }
}
