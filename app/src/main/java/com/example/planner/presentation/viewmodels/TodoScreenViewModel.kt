package com.example.planner.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planner.domain.interactors.AuthInteractor
import com.example.planner.domain.interactors.TodoInteractor
import com.example.planner.domain.models.TodoModel
import com.example.planner.presentation.viewdata.TodoViewData
import com.example.planner.presentation.viewdata.UserViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoScreenViewModel @Inject constructor(
    private val todoInteractor: TodoInteractor,
    private val authInteractor: AuthInteractor,
) : ViewModel() {
    private var user: Int? = null

    private var _todos = MutableStateFlow<List<TodoViewData>>(listOf())
    val todos = _todos.asStateFlow()

    init {
        viewModelScope.launch {

            user = authInteractor.getUser().id

            todoInteractor.get(user).collect {lst ->
                _todos.value = lst.map {
                    TodoViewData.fromModel(it)
                }
            }
        }
    }

    fun add(todo: String) {
        viewModelScope.launch {
            todoInteractor.add(TodoModel(todo, user!!))
        }
    }

    fun logout() {
        viewModelScope.launch {
            authInteractor.logout()
        }
        user = null
    }
}