package com.example.planner.presentation.screens

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object Todo : Routes("Todo")
}
