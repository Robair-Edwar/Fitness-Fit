package com.example.fitnessfit.presentation.screen.auth.login

sealed class LoginScreenEvent {
    object ClickLogin: LoginScreenEvent()
    object ClickSendEmail: LoginScreenEvent()
    object ClickForgotPassword: LoginScreenEvent()
    object DismissBottomSheet: LoginScreenEvent()
    data class EnterEmail(val email: String):LoginScreenEvent()
    data class EnterBottomSheetEmail(val email: String):LoginScreenEvent()
    data class EnterPassword(val password: String):LoginScreenEvent()
}