package com.dylanloyola.dailyflow.data.model

import java.util.Date

data class TaskRecord(
    val id: String = "",
    val taskId: String = "",
    val userId: String = "",
    val date: Long = 0, // Timestamp
    val isCompleted: Boolean = false
)
