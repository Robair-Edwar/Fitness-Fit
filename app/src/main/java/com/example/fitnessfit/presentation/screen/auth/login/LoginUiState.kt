package com.example.fitnessfit.presentation.screen.auth.login

import androidx.annotation.StringRes

data class LoginUiState(
    val email: String = "",
    @StringRes val emailFormatError: Int = -1,
    val password: String = "",
    @StringRes val passwordFormatError: Int = -1,
    val isLoading: Boolean = false,
    val isLogInSuccess: Boolean = false,
    @StringRes val loginError: Int = -1,
    val isBottomSheetOpen: Boolean = false,
    val bottomSheetEmail: String = "",
    @StringRes val bottomSheetSendState: Int = -1,
    @StringRes val bottomSheetEmailFormat: Int = -1,
)