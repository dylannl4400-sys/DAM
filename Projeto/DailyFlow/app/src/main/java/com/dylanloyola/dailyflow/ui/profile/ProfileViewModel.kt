package com.dylanloyola.dailyflow.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylanloyola.dailyflow.data.repository.AuthRepository
import com.dylanloyola.dailyflow.data.repository.RoutineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ProfileUiState(
    val name: String = "",
    val email: String = "",
    val streak: Int = 0,
    val totalTasksCompleted: Int = 0,
    val level: String = "Bronze"
)

class ProfileViewModel(
    private val authRepository: AuthRepository = AuthRepository(),
    private val routineRepository: RoutineRepository = RoutineRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    init {
        loadProfile()
    }

    private fun loadProfile() {
        val user = authRepository.currentUser ?: return
        viewModelScope.launch {
            val streak = routineRepository.calculateStreak(user.uid)
            _uiState.value = ProfileUiState(
                name = user.displayName ?: "User",
                email = user.email ?: "",
                streak = streak,
                totalTasksCompleted = 124, // Hardcoded for demo
                level = when {
                    streak > 30 -> "Diamond"
                    streak > 15 -> "Gold"
                    streak > 7 -> "Silver"
                    else -> "Bronze"
                }
            )
        }
    }

    fun logout() {
        authRepository.logout()
    }
}
