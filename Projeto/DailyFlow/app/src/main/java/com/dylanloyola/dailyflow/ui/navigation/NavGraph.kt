package com.dylanloyola.dailyflow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dylanloyola.dailyflow.ui.auth.LoginScreen
import com.dylanloyola.dailyflow.ui.auth.RegisterScreen
import com.dylanloyola.dailyflow.ui.home.HomeScreen
import com.dylanloyola.dailyflow.ui.profile.ProfileScreen
import com.dylanloyola.dailyflow.ui.routine.AddTaskScreen
import com.dylanloyola.dailyflow.ui.routine.CreateRoutineScreen
import com.dylanloyola.dailyflow.ui.routine.RoutineDetailScreen

@Composable
fun DailyFlowNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Auth.route,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        navigation(
            startDestination = Screen.Login.route,
            route = Screen.Auth.route
        ) {
            composable(Screen.Login.route) {
                LoginScreen(navController)
            }
            composable(Screen.Register.route) {
                RegisterScreen(navController)
            }
        }
        
        navigation(
            startDestination = Screen.Today.route,
            route = Screen.Main.route
        ) {
            composable(Screen.Today.route) {
                HomeScreen()
            }
            composable(Screen.Routines.route) {
                // RoutinesScreen(navController)
            }
            composable(Screen.Stats.route) {
                // StatsScreen(navController)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController)
            }
            
            composable(Screen.CreateRoutine.route) {
                CreateRoutineScreen(navController)
            }
            composable(Screen.RoutineDetail.route) { backStackEntry ->
                val routineId = backStackEntry.arguments?.getString("routineId") ?: ""
                RoutineDetailScreen(navController, routineId)
            }
            composable(Screen.AddTask.route) { backStackEntry ->
                val routineId = backStackEntry.arguments?.getString("routineId") ?: ""
                AddTaskScreen(navController, routineId)
            }
        }
    }
}
