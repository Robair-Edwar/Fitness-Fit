package com.example.fitnessfit.presentation.screen.auth.register

import androidx.annotation.StringRes

data class RegisterUiState (
    val name: String = "",
    @StringRes val nameError: Int = -1,
    val email: String = "",
    @StringRes val emailError: Int = -1,
    val password: String = "",
    @StringRes val passwordError: Int = -1,
    val isLoading: Boolean = false,
    val isSignedUpSuccess: Boolean = false,
    @StringRes val registerError: Int = -1
)