package com.example.fitnessfit.domain.repository

import com.example.fitnessfit.domain.models.ExercisePrediction
import com.example.fitnessfit.presentation.screen.dashboard.UserDashboard
import kotlinx.coroutines.flow.StateFlow

interface FireStoreRepository {
    suspend fun saveUserDashboard(user: UserDashboard)
    val userDashboard: StateFlow<UserDashboard?>
    fun startListeningToUserDashboard()
    suspend fun saveUserPlan(plan: ExercisePrediction)
    suspend fun readUserPlan(): ExercisePrediction?
    suspend fun updateUserDashboardField(field: String, value: Any)
}