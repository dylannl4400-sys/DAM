package com.dylanloyola.dailyflow.ui.routine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylanloyola.dailyflow.data.model.Routine
import com.dylanloyola.dailyflow.data.model.RoutinePeriod
import com.dylanloyola.dailyflow.data.model.Task
import com.dylanloyola.dailyflow.data.repository.AuthRepository
import com.dylanloyola.dailyflow.data.repository.RoutineRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RoutineViewModel(
    private val authRepository: AuthRepository = AuthRepository(),
    private val routineRepository: RoutineRepository = RoutineRepository()
) : ViewModel() {

    private val userId = authRepository.currentUser?.uid ?: ""

    val routines: Flow<List<Routine>> = routineRepository.getRoutines(userId)

    fun getTasks(routineId: String): Flow<List<Task>> = routineRepository.getTasks(routineId)

    fun createRoutine(name: String, period: RoutinePeriod, weekdays: List<Int>, onComplete: (String) -> Unit) {
        viewModelScope.launch {
            val routine = Routine(name = name, period = period, weekdays = weekdays, userId = userId)
            val id = routineRepository.addRoutine(routine)
            onComplete(id)
        }
    }

    fun addTask(routineId: String, name: String, category: String, duration: Int) {
        viewModelScope.launch {
            val task = Task(name = name, category = category, durationMinutes = duration, routineId = routineId)
            routineRepository.addTask(task)
        }
    }
}
