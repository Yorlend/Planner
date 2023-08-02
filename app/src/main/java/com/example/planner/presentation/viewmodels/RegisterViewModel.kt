package com.example.planner.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planner.domain.interactors.AuthInteractor
import com.example.planner.presentation.viewdata.UserViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
) : ViewModel() {

    private val _state = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val state = _state.asStateFlow()

    sealed class RegisterState {
        object Idle : RegisterState()
        object Loading : RegisterState()
        data class Success(val user: UserViewData) : RegisterState()
        data class Error(val message: String) : RegisterState()
    }

    fun register(username: String, password: String) {
        viewModelScope.launch {
            _state.value = RegisterState.Loading
            try {
                val user = authInteractor.register(username, password)
                _state.value = RegisterState.Success(UserViewData.fromModel(user))
            } catch (e: Exception) {
                _state.value = RegisterState.Error(e.message ?: "Something went wrong")
            }
        }
    }
}