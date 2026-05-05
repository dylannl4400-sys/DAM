package com.dylanloyola.dailyflow.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylanloyola.dailyflow.data.model.Routine
import com.dylanloyola.dailyflow.data.model.Task
import com.dylanloyola.dailyflow.data.model.TaskRecord
import com.dylanloyola.dailyflow.data.repository.AuthRepository
import com.dylanloyola.dailyflow.data.repository.RoutineRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

data class HomeUiState(
    val userName: String = "",
    val streak: Int = 0,
    val progress: Float = 0f,
    val tasksLeft: Int = 0,
    val morningTasks: List<TaskWithStatus> = emptyList(),
    val afternoonTasks: List<TaskWithStatus> = emptyList(),
    val nightTasks: List<TaskWithStatus> = emptyList()
)

data class TaskWithStatus(
    val task: Task,
    val isCompleted: Boolean
)

class HomeViewModel(
    private val authRepository: AuthRepository = AuthRepository(),
    private val routineRepository: RoutineRepository = RoutineRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        val userId = authRepository.currentUser?.uid ?: return
        
        viewModelScope.launch {
            // Get user name (from Firestore ideally, but using display name for now)
            val name = authRepository.currentUser?.displayName ?: "User"
            _uiState.update { it.copy(userName = name) }
            
            // Calculate streak
            val streak = routineRepository.calculateStreak(userId)
            _uiState.update { it.copy(streak = streak) }
            
            // Get today's tasks and progress
            val today = System.currentTimeMillis()
            combine(
                routineRepository.getRoutines(userId),
                routineRepository.getTaskRecordsForDate(userId, today)
            ) { routines, records ->
                val calendar = Calendar.getInstance()
                val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
                
                val activeRoutines = routines.filter { it.weekdays.contains(dayOfWeek) }
                
                // This is a bit complex for a combine block, normally I'd fetch tasks separately
                // but let's simplify for the demonstration
                val allTasksWithStatus = mutableListOf<TaskWithStatus>()
                
                for (routine in activeRoutines) {
                    // This is not ideal inside a flow collector, but for simplicity:
                    val tasks = routineRepository.getTasks(routine.id).first()
                    tasks.forEach { task ->
                        val isCompleted = records.any { it.taskId == task.id && it.isCompleted }
                        allTasksWithStatus.add(TaskWithStatus(task, isCompleted))
                    }
                }
                
                val total = allTasksWithStatus.size
                val completed = allTasksWithStatus.count { it.isCompleted }
                val progress = if (total > 0) completed.toFloat() / total else 0f
                
                _uiState.update { state ->
                    state.copy(
                        progress = progress,
                        tasksLeft = total - completed,
                        // Categorize by period (assuming tasks inherit routine period)
                        // This would need more data linking in a real app
                    )
                }
            }.collect()
        }
    }

    fun toggleTask(taskId: String, isCompleted: Boolean) {
        val userId = authRepository.currentUser?.uid ?: return
        viewModelScope.launch {
            routineRepository.toggleTaskCompletion(taskId, userId, System.currentTimeMillis(), isCompleted)
            // Refresh streak if full day completed
            val streak = routineRepository.calculateStreak(userId)
            _uiState.update { it.copy(streak = streak) }
        }
    }
}
