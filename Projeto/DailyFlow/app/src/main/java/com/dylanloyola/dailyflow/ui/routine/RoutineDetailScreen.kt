package com.dylanloyola.dailyflow.ui.routine

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dylanloyola.dailyflow.ui.home.TaskItem
import com.dylanloyola.dailyflow.ui.navigation.Screen
import com.dylanloyola.dailyflow.ui.theme.AccentPurple
import com.dylanloyola.dailyflow.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineDetailScreen(
    navController: NavController,
    routineId: String,
    viewModel: RoutineViewModel = viewModel()
) {
    val tasks by viewModel.getTasks(routineId).collectAsState(initial = emptyList())
    // For demo purposes, we'll assume a name if we can't find it
    val routineName = "Morning Energy" 

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(routineName, fontWeight = FontWeight.Bold, color = PrimaryBlue) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = PrimaryBlue)
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More", tint = PrimaryBlue)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddTask.createRoute(routineId)) },
                containerColor = PrimaryBlue,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD).copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                Text("Estimated Time", style = MaterialTheme.typography.labelMedium)
                                Text("45 mins", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                            }
                            Surface(
                                color = Color(0xFFE1BEE7),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("${tasks.size} Tasks", modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        LinearProgressIndicator(
                            progress = 0.25f, // Hardcoded for demo
                            modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                            color = Color(0xFF2E7D32),
                            trackColor = Color.LightGray.copy(alpha = 0.3f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("1 of ${tasks.size} tasks completed", style = MaterialTheme.typography.labelSmall)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            items(tasks) { task ->
                TaskItem(
                    title = task.name,
                    subtitle = "${task.durationMinutes} min • ${task.category}",
                    isCompleted = false, // Status would come from another source in real app
                    onToggle = { /* TODO */ }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            
            // Placeholder tasks if empty
            if (tasks.isEmpty()) {
                item {
                    TaskItem("Sun Salutation", "15 min • Exercise", true) {}
                    Spacer(modifier = Modifier.height(12.dp))
                    TaskItem("Hydrate & Coffee", "10 min • Nutrition", false) {}
                    Spacer(modifier = Modifier.height(12.dp))
                    TaskItem("Guided Meditation", "10 min • Mindset", false) {}
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            item {
                Row(modifier = Modifier.fillMaxWidth().height(200.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Card(
                        modifier = Modifier.weight(1.2f).fillMaxHeight(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = AccentPurple)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text("Morning Streak", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            Text("You're on a 5-day flow state! Keep it up.", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                            Spacer(modifier = Modifier.weight(1f))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Stars, contentDescription = null, tint = Color.White)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Gold Level", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color.Gray)
                    ) {
                        // Image placeholder
                    }
                }
            }
        }
    }
}
