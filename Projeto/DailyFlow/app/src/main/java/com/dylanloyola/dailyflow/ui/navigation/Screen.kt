package com.dylanloyola.dailyflow.ui.navigation

sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Login : Screen("login")
    object Register : Screen("register")
    
    object Main : Screen("main")
    object Today : Screen("today")
    object Routines : Screen("routines")
    object Stats : Screen("stats")
    object Profile : Screen("profile")
    
    object CreateRoutine : Screen("create_routine")
    object RoutineDetail : Screen("routine_detail/{routineId}") {
        fun createRoute(routineId: String) = "routine_detail/$routineId"
    }
    object AddTask : Screen("add_task/{routineId}") {
        fun createRoute(routineId: String) = "add_task/$routineId"
    }
}
