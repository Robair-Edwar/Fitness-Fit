package com.example.fitnessfit.presentation.screen.settings

sealed class SettingsEvents {
    data class ChangeLanguage(val language: String) : SettingsEvents()
    data class ChangeTheme(val theme: Int): SettingsEvents()
    data class EnterOldPassword(val password: String): SettingsEvents()
    data class EnterNewPassword(val password: String): SettingsEvents()
    object ChangePassword: SettingsEvents()
    object VerifyAccount: SettingsEvents()
    object LogOut: SettingsEvents()
    object ShowDialog: SettingsEvents()
    object DismissDialog: SettingsEvents()
    object DismissBottomSheet: SettingsEvents()
    object ShowBottomSheet: SettingsEvents()
}