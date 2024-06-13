package com.example.fitnessfit.data.remote.repository

import android.util.Log
import com.example.fitnessfit.domain.repository.AuthenticationRepository
import com.example.fitnessfit.presentation.screen.dashboard.UserDashboard
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthenticationRepository {

    init {
        isEmailVerified()
    }

    override suspend fun login(email: String, password: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            val user = auth.currentUser
            user?.reload()?.await()
            user?.isEmailVerified ?: false
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun forgotPassword(email: String): Boolean {
        return try {
            auth.sendPasswordResetEmail(email).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun isEmailVerified(): Flow<Boolean> {
        return flow {
            val user = currentUser()
            if (user != null) {
                user.reload().await()
                emit(user.isEmailVerified)
            } else {
                emit(false)
            }
        }.catch { e ->
            Log.e("isEmailVerified", "Error reloading user or handling flow", e)
            emit(false)
        }
    }


    override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun sendEmailVerification(): Boolean {
        return try {
            val user = currentUser()
            user?.let {
                it.sendEmailVerification().await()
                true
            } ?: false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun changePassword(newPassword: String, userPassword: String): Boolean {
        return try {
            val isVerified = isEmailVerified().first()
            if (!isVerified) {
                Log.d("Change Password", "Email is not verified")
                return false
            }

            val user = FirebaseAuth.getInstance().currentUser
            if (user == null) {
                Log.d("Change Password", "No current user")
                return false
            }

            val email = user.email
            if (email.isNullOrEmpty()) {
                Log.d("Change Password", "User email is null or empty")
                return false
            }

            // Re-authenticate the user
            val reauthResult = reauthenticateUser(email, userPassword)
            if (!reauthResult) {
                Log.d("Change Password", "Re-authentication failed")
                return false
            }

            // Change the password
            user.updatePassword(newPassword).await()
            Log.d("Change Password", "Password changed successfully")
            true
        } catch (e: FirebaseAuthRecentLoginRequiredException) {
            Log.d("Change Password", "Recent login required")
            false
        } catch (e: Exception) {
            Log.e("Change Password Error", "Error updating password", e)
            false
        }
    }

    private suspend fun reauthenticateUser(email: String, password: String): Boolean {
        return try {
            val user = FirebaseAuth.getInstance().currentUser
            if (user == null) {
                Log.d("Re-authenticate", "No current user")
                return false
            }

            val credential = EmailAuthProvider.getCredential(email, password)
            user.reauthenticate(credential).await()
            true
        } catch (e: Exception) {
            Log.e("Re-authenticate Error", "Error re-authenticating user", e)
            false
        }
    }

    override suspend fun signOut(): Boolean {
        return try {
            auth.signOut()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun isSignedIn(): Boolean {
        return auth.currentUser != null
    }

    override fun currentUser(): FirebaseUser? {
        return auth.currentUser
    }
}
