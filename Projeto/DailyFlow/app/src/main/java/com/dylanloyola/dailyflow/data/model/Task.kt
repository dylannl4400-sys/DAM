package com.dylanloyola.dailyflow.data.model

data class Task(
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val durationMinutes: Int = 0,
    val isRecurring: Boolean = true,
    val routineId: String = ""
)
