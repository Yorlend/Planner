package com.example.planner.presentation.screens

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object Register: Routes("Register")
    object Todo : Routes("Todo")
}
