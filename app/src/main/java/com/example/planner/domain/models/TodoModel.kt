package com.example.planner.domain.models

data class TodoModel(
    val todo: String,
    val user: Int,
    val completed: Boolean = false,
    val id: Int? = null,
    val isDeleted: Boolean = false,
)