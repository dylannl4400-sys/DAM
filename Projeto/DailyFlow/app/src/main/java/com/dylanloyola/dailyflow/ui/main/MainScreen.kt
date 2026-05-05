package com.dylanloyola.dailyflow.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dylanloyola.dailyflow.ui.navigation.DailyFlowNavGraph
import com.dylanloyola.dailyflow.ui.navigation.Screen

sealed class BottomNavItem(val screen: Screen, val title: String, val icon: ImageVector) {
    object Today : BottomNavItem(Screen.Today, "Today", Icons.Default.CalendarToday)
    object Routines : BottomNavItem(Screen.Routines, "Routines", Icons.Default.List)
    object Stats : BottomNavItem(Screen.Stats, "Stats", Icons.Default.BarChart)
    object Profile : BottomNavItem(Screen.Profile, "Profile", Icons.Default.Person)
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navItems = listOf(
        BottomNavItem.Today,
        BottomNavItem.Routines,
        BottomNavItem.Stats,
        BottomNavItem.Profile
    )

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            
            // Only show bottom bar on main screens
            val showBottomBar = navItems.any { it.screen.route == currentDestination?.route }
            
            if (showBottomBar) {
                NavigationBar {
                    navItems.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == item.screen.route } == true,
                            onClick = {
                                navController.navigate(item.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        DailyFlowNavGraph(
            navController = navController,
            startDestination = Screen.Auth.route,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
