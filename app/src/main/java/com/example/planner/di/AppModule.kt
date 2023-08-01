package com.example.planner.di

import com.example.planner.domain.interactors.AuthInteractor
import com.example.planner.domain.interactors.TodoInteractor
import com.example.planner.domain.repositories.AuthRepository
import com.example.planner.domain.repositories.TodoRepository
import com.example.planner.presentation.mock.MockAuthRepository
import com.example.planner.presentation.mock.MockTodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMockTodoRepo(): TodoRepository {
        return MockTodoRepository()
    }

    @Provides
    @Singleton
    fun provideMockAuthRepo(): AuthRepository {
        return MockAuthRepository()
    }

    @Provides
    @Singleton
    fun provideTodoInteractor(repo: TodoRepository): TodoInteractor {
        return TodoInteractor(repo)
    }

    @Provides
    @Singleton
    fun provideAuthInteractor(repo: AuthRepository): AuthInteractor {
        return AuthInteractor(repo)
    }
}
