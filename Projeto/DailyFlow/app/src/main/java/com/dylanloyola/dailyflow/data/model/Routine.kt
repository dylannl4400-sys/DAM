package com.dylanloyola.dailyflow.data.model

enum class RoutinePeriod {
    MORNING, AFTERNOON, NIGHT
}

data class Routine(
    val id: String = "",
    val name: String = "",
    val period: RoutinePeriod = RoutinePeriod.MORNING,
    val weekdays: List<Int> = emptyList(), // 1 (Sunday) to 7 (Saturday)
    val userId: String = ""
)
