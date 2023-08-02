package com.example.planner.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planner.domain.interactors.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
) : ViewModel() {

    private val _session = MutableStateFlow<Int>(-1)
    val session = _session.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val id = authInteractor.getUser().id
                _session.value = id!!
            } catch (e: Exception) {
                _session.value = -1
            }
        }
    }
}