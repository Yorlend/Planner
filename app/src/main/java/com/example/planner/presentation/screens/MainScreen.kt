package com.example.planner.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ScreenMain() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        
        composable(Routes.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(Routes.Todo.route) {
            TodoScreen(navController = navController)
        }
    }
}
