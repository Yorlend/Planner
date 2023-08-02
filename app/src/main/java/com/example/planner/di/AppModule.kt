package com.example.planner.di

import android.content.Context
import androidx.room.Room
import com.example.planner.data.db.AppDatabase
import com.example.planner.data.db.SessionManager
import com.example.planner.data.repositories.AuthRepositoryImpl
import com.example.planner.data.repositories.TodoRepositoryImpl
import com.example.planner.domain.interactors.AuthInteractor
import com.example.planner.domain.interactors.TodoInteractor
import com.example.planner.domain.repositories.AuthRepository
import com.example.planner.domain.repositories.TodoRepository
import com.example.planner.presentation.mock.MockAuthRepository
import com.example.planner.presentation.mock.MockTodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "plannerLocalDB"
    ).build()

    @Provides
    @Singleton
    fun provideTodoRepo(db: AppDatabase): TodoRepository {
        return TodoRepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun provideAuthRepo(db: AppDatabase, sessionManager: SessionManager): AuthRepository {
        return AuthRepositoryImpl(db, sessionManager)
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

    @Provides
    @Singleton
    fun provideSessionManager(
        @ApplicationContext app: Context
    ) = SessionManager(app)
}
