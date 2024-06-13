package com.example.fitnessfit.presentation.screen.auth.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessfit.R
import com.example.fitnessfit.domain.models.ExercisePrediction
import com.example.fitnessfit.domain.repository.AuthenticationRepository
import com.example.fitnessfit.domain.repository.FireStoreRepository
import com.example.fitnessfit.presentation.screen.dashboard.util.toUser
import com.example.fitnessfit.presentation.screen.on_boarding.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val fireStoreRepository: FireStoreRepository,
) : ViewModel() {

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState = _registerUiState.asStateFlow()

    fun clickEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnterName -> enterName(event.name)
            is RegisterEvent.EnterEmail -> enterEmail(event.email)
            is RegisterEvent.EnterPassword -> enterPassword(event.password)
            RegisterEvent.ClickRegister -> signUp()
        }
    }

    private fun enterName(name: String) {
        _registerUiState.value = registerUiState.value.copy(
            name = name,
            nameError = -1
        )
    }

    private fun enterEmail(email: String) {
        _registerUiState.value = registerUiState.value.copy(
            email = email,
            emailError = -1
        )
    }

    private fun enterPassword(password: String) {
        _registerUiState.value = registerUiState.value.copy(
            password = password,
            passwordError = -1
        )
    }

    private fun signUp() {
        if (validateName(registerUiState.value.name)) {
            _registerUiState.value = registerUiState.value.copy(nameError = -1)
            if (validateEmail(registerUiState.value.email)) {
                _registerUiState.value = registerUiState.value.copy(emailError = -1)
                if (validatePassword(registerUiState.value.password)) {
                    _registerUiState.value = registerUiState.value.copy(passwordError = -1)
                    _registerUiState.value = registerUiState.value.copy(isLoading = true)
                    viewModelScope.launch {
                        val result = authenticationRepository.signUp(registerUiState.value.email, registerUiState.value.password)
                        _registerUiState.value = if (result) {
                            User.name = registerUiState.value.name
                            authenticationRepository.sendEmailVerification()
                            fireStoreRepository.saveUserDashboard(User.toUser())
                            registerUiState.value.copy(isSignedUpSuccess = true, isLoading = false)
                        } else {
                            registerUiState.value.copy(registerError = R.string.register_screen_registerError, isLoading = false)
                        }
                    }
                } else {
                    _registerUiState.value = registerUiState.value.copy(passwordError = R.string.register_screen_passwordLess, isLoading = false)
                }
            } else {
                _registerUiState.value = registerUiState.value.copy(emailError = R.string.register_screen_emailFormat, isLoading = false)
            }
        } else {
            _registerUiState.value = registerUiState.value.copy(nameError = R.string.register_screen_nameError, isLoading = false)
        }
    }

    private fun validateName(name: String): Boolean {
        return name.length in 1..9
    }

    private fun validateEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validatePassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 8 && !password.contains(Regex("\\s"))
    }

    fun clearErrors() {
        _registerUiState.value = registerUiState.value.copy(
            registerError = -1
        )
    }

    fun isSignIn(): Boolean {
        return authenticationRepository.isSignedIn()
    }

    fun saveUserPlan(plan: List<List<String>>){
        val exercisePrediction =
            plan.let {
                ExercisePrediction(
                    day1 = it.getOrNull(0) ?: emptyList(),
                    day2 = it.getOrNull(1) ?: emptyList(),
                    day3 = it.getOrNull(2) ?: emptyList(),
                    day4 = it.getOrNull(3) ?: emptyList(),
                    day5 = it.getOrNull(4) ?: emptyList(),
                    day6 = it.getOrNull(5) ?: emptyList(),
                    day7 = it.getOrNull(6) ?: emptyList(),
                    day8 = it.getOrNull(7) ?: emptyList(),
                    day9 = it.getOrNull(8) ?: emptyList(),
                    day10 = it.getOrNull(9) ?: emptyList(),
                    day11 = it.getOrNull(10) ?: emptyList(),
                    day12 = it.getOrNull(11) ?: emptyList(),
                    day13 = it.getOrNull(12) ?: emptyList(),
                    day14 = it.getOrNull(13) ?: emptyList(),
                    day15 = it.getOrNull(14) ?: emptyList(),
                    day16 = it.getOrNull(15) ?: emptyList(),
                    day17 = it.getOrNull(16) ?: emptyList(),
                    day18 = it.getOrNull(17) ?: emptyList(),
                    day19 = it.getOrNull(18) ?: emptyList(),
                    day20 = it.getOrNull(19) ?: emptyList(),
                    day21 = it.getOrNull(20) ?: emptyList(),
                    day22 = it.getOrNull(21) ?: emptyList(),
                    day23 = it.getOrNull(22) ?: emptyList(),
                    day24 = it.getOrNull(23) ?: emptyList()
                )
            }
        viewModelScope.launch {
            fireStoreRepository.saveUserPlan(exercisePrediction)
        }
    }

    fun clearUiState() {
        _registerUiState.value = RegisterUiState()
    }
}
