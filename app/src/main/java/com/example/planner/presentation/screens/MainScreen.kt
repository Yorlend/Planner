package com.example.planner.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planner.presentation.viewmodels.MainScreenViewModel

@Composable
fun ScreenMain() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<MainScreenViewModel>()
    val session by viewModel.session.collectAsState()

    val startDestination = when(val userId = session) {
        -1 -> Routes.Login.route
        else -> Routes.Todo.route
    }

    NavHost(navController = navController, startDestination = startDestination) {
        
        composable(Routes.Login.route) {
            LoginScreen(navController = navController)
        }
        
        composable(Routes.Register.route) {
            RegisterScreen(navController = navController)
        }

        composable(Routes.Todo.route) {
            TodoScreen(navController = navController)
        }
    }
}
