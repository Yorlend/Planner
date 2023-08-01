package com.example.planner.data.entities

data class TodoEntity(
    val todo: String,
    val completed: Boolean,
    val user: Int,
    val id: Int?,
)
