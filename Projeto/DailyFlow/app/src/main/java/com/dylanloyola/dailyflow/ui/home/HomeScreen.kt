package com.dylanloyola.dailyflow.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dylanloyola.dailyflow.ui.theme.AccentPurple
import com.dylanloyola.dailyflow.ui.theme.PrimaryBlue
import com.dylanloyola.dailyflow.ui.theme.SecondaryBlue
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val dateStr = remember { SimpleDateFormat("EEEE, MMMM d", Locale.getDefault()).format(Date()).uppercase() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 80.dp)
    ) {
        // Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* TODO */ }) {
                    Icon(Icons.Default.Settings, contentDescription = "Settings", tint = PrimaryBlue)
                }
                Text(
                    text = "DailyFlow",
                    style = MaterialTheme.typography.titleLarge,
                    color = PrimaryBlue,
                    fontWeight = FontWeight.Bold
                )
                Surface(
                    modifier = Modifier.size(40.dp),
                    shape = CircleShape,
                    color = Color.LightGray
                ) {
                    // Placeholder for profile image
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(text = dateStr, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Good Morning,\n${uiState.userName}",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black
                )
                
                // Streak Badge
                Surface(
                    color = AccentPurple,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Whatshot, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("${uiState.streak} days", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Progress Card
            ProgressCard(progress = uiState.progress, tasksLeft = uiState.tasksLeft)
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Section Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.WbSunny, contentDescription = null, tint = Color(0xFFE65100))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Morning Routines", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                }
                TextButton(onClick = { /* TODO */ }) {
                    Text("Edit")
                }
            }
        }
        
        // Task Items (Simulated for Morning)
        items(uiState.morningTasks) { taskWithStatus ->
            TaskItem(
                title = taskWithStatus.task.name,
                subtitle = "${taskWithStatus.task.durationMinutes} min",
                isCompleted = taskWithStatus.isCompleted,
                onToggle = { viewModel.toggleTask(taskWithStatus.task.id, it) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Example Task Items (Placeholder if empty)
        if (uiState.morningTasks.isEmpty()) {
            item {
                TaskItem("Hydrate", "500ml • 2 min", true) {}
                Spacer(modifier = Modifier.height(8.dp))
                TaskItem("Meditation", "Mindfulness • 10 min", false) {}
                Spacer(modifier = Modifier.height(8.dp))
                TaskItem("Morning Run", "3km • 25 min", false) {}
            }
        }
        
        item {
            Spacer(modifier = Modifier.height(24.dp))
            // Weekly Average and Sleep Cards (Simulated)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "85%",
                    subtitle = "Weekly Average",
                    icon = Icons.Default.Timeline,
                    color = Color(0xFFE3F2FD)
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "7h 45m",
                    subtitle = "Avg Sleep",
                    icon = Icons.Default.Hotel,
                    color = Color(0xFFE8F5E9)
                )
            }
        }
    }
}

@Composable
fun ProgressCard(progress: Float, tasksLeft: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(160.dp)) {
                Canvas(modifier = Modifier.size(140.dp)) {
                    drawArc(
                        color = Color(0xFFF1F1F1),
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = PrimaryBlue,
                        startAngle = -90f,
                        sweepAngle = 360f * progress,
                        useCenter = false,
                        style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${(progress * 100).toInt()}%",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue
                    )
                    Text(
                        text = "COMPLETED",
                        style = MaterialTheme.typography.labelSmall,
                        color = PrimaryBlue
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (tasksLeft > 0) "$tasksLeft tasks left to reach your daily goal" else "Goal reached! 🎉",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun TaskItem(title: String, subtitle: String, isCompleted: Boolean, onToggle: (Boolean) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = SecondaryBlue
                ) {
                    Icon(
                        Icons.Default.Check, // Replace with category icon
                        contentDescription = null,
                        modifier = Modifier.padding(12.dp),
                        tint = PrimaryBlue
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = subtitle, fontSize = 14.sp, color = Color.Gray)
                }
            }
            
            IconButton(
                onClick = { onToggle(!isCompleted) },
                modifier = Modifier
                    .size(32.dp)
                    .background(if (isCompleted) Color(0xFF1B5E20) else Color.Transparent, CircleShape)
            ) {
                if (isCompleted) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                } else {
                    Box(modifier = Modifier.size(24.dp).background(Color.Transparent, CircleShape).padding(2.dp).background(Color.White, CircleShape)) {
                        Surface(modifier = Modifier.fillMaxSize(), shape = CircleShape, border = ButtonDefaults.outlinedButtonBorder, color = Color.Transparent) {}
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(modifier: Modifier, title: String, subtitle: String, icon: ImageVector, color: Color) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = RoundedCornerShape(8.dp),
                color = Color.White
            ) {
                Icon(icon, contentDescription = null, modifier = Modifier.padding(8.dp), tint = Color.Black)
            }
            Spacer(modifier = Modifier.height(48.dp))
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = subtitle, fontSize = 14.sp, color = Color.Gray)
        }
    }
}
