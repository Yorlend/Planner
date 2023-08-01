package com.example.planner.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planner.domain.interactors.AuthInteractor
import com.example.planner.domain.models.UserModel
import com.example.planner.presentation.viewdata.UserViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state = _state.asStateFlow()

    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        data class Success(val user: UserViewData) : LoginState()
        data class Error(val message: String) : LoginState()
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            try {
                val user = authInteractor.login(username, password)
                _state.value = LoginState.Success(UserViewData.fromModel(user))
            } catch (e: Exception) {
                _state.value = LoginState.Error(e.message ?: "Something went wrong")
            }
        }
    }
}