package com.dylanloyola.dailyflow.data.repository

import com.dylanloyola.dailyflow.data.model.Routine
import com.dylanloyola.dailyflow.data.model.Task
import com.dylanloyola.dailyflow.data.model.TaskRecord
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.*

class RoutineRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    // --- Routines ---
    fun getRoutines(userId: String): Flow<List<Routine>> = callbackFlow {
        val subscription = firestore.collection("routines")
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, _ ->
                snapshot?.let {
                    trySend(it.toObjects(Routine::class.java))
                }
            }
        awaitClose { subscription.remove() }
    }

    suspend fun addRoutine(routine: Routine): String {
        val doc = firestore.collection("routines").document()
        val newRoutine = routine.copy(id = doc.id)
        doc.set(newRoutine).await()
        return doc.id
    }

    // --- Tasks ---
    fun getTasks(routineId: String): Flow<List<Task>> = callbackFlow {
        val subscription = firestore.collection("tasks")
            .whereEqualTo("routineId", routineId)
            .addSnapshotListener { snapshot, _ ->
                snapshot?.let {
                    trySend(it.toObjects(Task::class.java))
                }
            }
        awaitClose { subscription.remove() }
    }

    suspend fun addTask(task: Task) {
        val doc = firestore.collection("tasks").document()
        val newTask = task.copy(id = doc.id)
        doc.set(newTask).await()
    }

    // --- Task Records (Completion) ---
    suspend fun toggleTaskCompletion(taskId: String, userId: String, date: Long, isCompleted: Boolean) {
        val dateString = formatDate(date)
        val recordId = "${taskId}_${dateString}"
        val doc = firestore.collection("taskRecords").document(recordId)
        
        if (isCompleted) {
            val record = TaskRecord(id = recordId, taskId = taskId, userId = userId, date = date, isCompleted = true)
            doc.set(record).await()
        } else {
            doc.delete().await()
        }
    }

    fun getTaskRecordsForDate(userId: String, date: Long): Flow<List<TaskRecord>> = callbackFlow {
        val startOfDay = getStartOfDay(date)
        val endOfDay = startOfDay + 86400000L
        
        val subscription = firestore.collection("taskRecords")
            .whereEqualTo("userId", userId)
            .whereGreaterThanOrEqualTo("date", startOfDay)
            .whereLessThan("date", endOfDay)
            .addSnapshotListener { snapshot, _ ->
                snapshot?.let {
                    trySend(it.toObjects(TaskRecord::class.java))
                }
            }
        awaitClose { subscription.remove() }
    }

    // --- Streak Logic ---
    suspend fun calculateStreak(userId: String): Int {
        var streak = 0
        var currentDate = Calendar.getInstance()
        // Start checking from today or yesterday
        // If today is fully completed, include it. Otherwise start from yesterday.
        
        val today = getStartOfDay(System.currentTimeMillis())
        if (isDayFullyCompleted(userId, today)) {
            streak++
        } else {
            // Check if today has any tasks at all. If not, maybe streak shouldn't break?
            // But usually streak is about consecutive days of completion.
        }

        var checkDate = today - 86400000L
        while (isDayFullyCompleted(userId, checkDate)) {
            streak++
            checkDate -= 86400000L
        }
        
        return streak
    }

    private suspend fun isDayFullyCompleted(userId: String, date: Long): Boolean {
        // 1. Get all routines for the user
        val routines = firestore.collection("routines")
            .whereEqualTo("userId", userId)
            .get().await().toObjects(Routine::class.java)
        
        // 2. Filter routines active on this day of the week
        val calendar = Calendar.getInstance().apply { timeInMillis = date }
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val activeRoutines = routines.filter { it.weekdays.contains(dayOfWeek) }
        
        if (activeRoutines.isEmpty()) return false // Or true if you don't want to break streak on rest days

        // 3. Get all tasks for these routines
        val tasks = mutableListOf<Task>()
        for (routine in activeRoutines) {
            val routineTasks = firestore.collection("tasks")
                .whereEqualTo("routineId", routine.id)
                .get().await().toObjects(Task::class.java)
            tasks.addAll(routineTasks)
        }
        
        if (tasks.isEmpty()) return false

        // 4. Get completed records for this date
        val startOfDay = getStartOfDay(date)
        val endOfDay = startOfDay + 86400000L
        val records = firestore.collection("taskRecords")
            .whereEqualTo("userId", userId)
            .whereGreaterThanOrEqualTo("date", startOfDay)
            .whereLessThan("date", endOfDay)
            .get().await().toObjects(TaskRecord::class.java)
        
        val completedTaskIds = records.filter { it.isCompleted }.map { it.taskId }.toSet()
        
        return tasks.all { completedTaskIds.contains(it.id) }
    }

    private fun getStartOfDay(timestamp: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    private fun formatDate(date: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        return "${calendar.get(Calendar.YEAR)}_${calendar.get(Calendar.MONTH) + 1}_${calendar.get(Calendar.DAY_OF_MONTH)}"
    }
}
