package com.dylanloyola.dailyflow.ui.routine

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dylanloyola.dailyflow.data.model.RoutinePeriod

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRoutineScreen(
    navController: NavController,
    viewModel: RoutineViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var selectedPeriod by remember { mutableStateOf(RoutinePeriod.MORNING) }
    var selectedDays by remember { mutableStateOf(setOf(2, 3, 4, 5, 6)) } // Mon-Fri default

    val daysOfWeek = listOf("S", "M", "T", "W", "T", "F", "S")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New Routine", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Text("Routine Name", fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("e.g. Morning Energy") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Period", fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                RoutinePeriod.values().forEach { period ->
                    FilterChip(
                        selected = selectedPeriod == period,
                        onClick = { selectedPeriod = period },
                        label = { Text(period.name.lowercase().capitalize()) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Weekdays", fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                daysOfWeek.forEachIndexed { index, day ->
                    val dayNum = index + 1
                    val isSelected = selectedDays.contains(dayNum)
                    
                    IconButton(
                        onClick = {
                            selectedDays = if (isSelected) selectedDays - dayNum else selectedDays + dayNum
                        },
                        modifier = Modifier.size(40.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                        )
                    ) {
                        Text(
                            text = day,
                            color = if (isSelected) Color.White else Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.createRoutine(name, selectedPeriod, selectedDays.toList()) { id ->
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                enabled = name.isNotBlank()
            ) {
                Text("Create Routine")
            }
        }
    }
}
