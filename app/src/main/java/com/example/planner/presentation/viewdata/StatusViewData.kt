package com.example.planner.presentation.viewdata

import androidx.compose.ui.graphics.Color

enum class StatusViewData(val color: Color) {
    COMPLETED(Color.Green),
    PENDING(Color.Yellow);

    companion object {
        fun fromModel(status: Boolean): StatusViewData {
            return when(status) {
                true -> COMPLETED
                else -> PENDING
            }
        }
    }

    fun toModel(): Boolean {
        return when(this) {
            COMPLETED -> true
            else -> false
        }
    }
}
