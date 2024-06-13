package com.example.fitnessfit.data.remote.repository

import com.example.fitnessfit.domain.models.ExercisePrediction
import com.example.fitnessfit.domain.repository.FireStoreRepository
import com.example.fitnessfit.presentation.screen.dashboard.UserDashboard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireStoreRepositoryImpl @Inject constructor(
    fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth
):FireStoreRepository {

    private val userDashboardCollection = fireStore.collection("userDashboards")
    private val userPlanCollection = fireStore.collection("userPlan")

    private val _userDashboard = MutableStateFlow<UserDashboard?>(null)
    override val userDashboard: StateFlow<UserDashboard?> get() = _userDashboard

    override suspend fun saveUserDashboard(user: UserDashboard) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            try {
                userDashboardCollection.document(userId).set(user).await()
                println("UserDashboard successfully written!")
            } catch (e: Exception) {
                println("Error writing UserDashboard: ${e.message}")
                e.printStackTrace()
            }
        } else {
            println("Error: No authenticated user found.")
        }
    }

    override suspend fun updateUserDashboardField(field: String, value: Any) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            try {
                userDashboardCollection.document(userId).update(field, value).await()
                println("UserDashboard field successfully updated!")
            } catch (e: Exception) {
                println("Error updating UserDashboard field: ${e.message}")
                e.printStackTrace()
            }
        } else {
            println("Error: No authenticated user found.")
        }
    }

    override fun startListeningToUserDashboard() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            userDashboardCollection.document(userId).addSnapshotListener { snapshot, e ->
                if (e != null) {
                    println("Error listening to UserDashboard: ${e.message}")
                    e.printStackTrace()
                    return@addSnapshotListener
                }
                if (snapshot != null && snapshot.exists()) {
                    _userDashboard.value = snapshot.toObject(UserDashboard::class.java)
                } else {
                    println("Current data: null")
                    _userDashboard.value = null
                }
            }
        } else {
            println("Error: No authenticated user found.")
        }
    }

    override suspend fun saveUserPlan(plan: ExercisePrediction) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            try {
                userPlanCollection.document(userId).set(plan).await()
                println("UserPlan successfully written!")
            } catch (e: Exception) {
                println("Error writing UserPlan: ${e.message}")
                e.printStackTrace()
            }
        } else {
            println("Error: No authenticated user found.")
        }
    }

    override suspend fun readUserPlan(): ExercisePrediction? {
        val currentUser = auth.currentUser
        return if (currentUser != null) {
            val userId = currentUser.uid
            try {
                val document = userPlanCollection.document(userId).get().await()
                if (document.exists()) {
                    document.toObject<ExercisePrediction>()
                } else {
                    println("No UserPlan found for user: $userId")
                    null
                }
            } catch (e: Exception) {
                println("Error reading UserPlan: ${e.message}")
                e.printStackTrace()
                null
            }
        } else {
            println("Error: No authenticated user found.")
            null
        }
    }
}