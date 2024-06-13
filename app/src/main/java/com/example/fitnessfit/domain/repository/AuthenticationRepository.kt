package com.example.fitnessfit.domain.repository

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun login(email: String, password: String): Boolean
    suspend fun signUp(email: String, password: String): Boolean
    suspend fun sendEmailVerification(): Boolean
    fun isEmailVerified(): Flow<Boolean>
    suspend fun forgotPassword(email: String): Boolean
    fun currentUser(): FirebaseUser?
    suspend fun changePassword(newPassword: String, userPassword: String): Boolean
    suspend fun signOut(): Boolean
    fun isSignedIn(): Boolean
}