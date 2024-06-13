package com.example.fitnessfit.presentation.screen.auth.register

sealed class RegisterEvent {
    data class EnterName(val name: String): RegisterEvent()
    data class EnterEmail(val email: String): RegisterEvent()
    data class EnterPassword(val password: String): RegisterEvent()
    object ClickRegister: RegisterEvent()
}