package com.example.fitnessfit.presentation.screen.settings

import androidx.annotation.StringRes

data class SettingsUiState(
    val language: String = "",
    @StringRes val setLanguageError: Int = -1,
    val theme: Int = 0,
    @StringRes val setThemeError: Int = -1,
    val isEmailVerified: Boolean = false,
    @StringRes val verifyAccountError: Int = -1,
    @StringRes val verifyAccountAlreadyVerified: Int = -1,
    @StringRes val verifyAccountEmailSent: Int = -1,
    @StringRes val oldPasswordFormatError: Int = -1,
    @StringRes val newPasswordFormatError: Int = -1,
    val oldPassword: String = "",
    val newPassword: String = "",
    @StringRes val changePasswordState: Int = -1,
    val isUserLogedOut: Boolean = false,
    val isBottomSheetOpen: Boolean = false,
    @StringRes val logOutError: Int = -1,
    val isDialogOpen: Boolean = false,
)