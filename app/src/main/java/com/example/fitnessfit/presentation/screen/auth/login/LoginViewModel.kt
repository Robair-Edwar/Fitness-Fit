package com.example.fitnessfit.presentation.screen.auth.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessfit.R
import com.example.fitnessfit.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {


    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    fun clickEvent(event: LoginScreenEvent) {
        when (event) {
            LoginScreenEvent.ClickForgotPassword -> openBottomSheet()
            LoginScreenEvent.DismissBottomSheet -> onDismissBottomSheet()
            is LoginScreenEvent.EnterBottomSheetEmail -> enterBottomSheetEmail(event.email)
            is LoginScreenEvent.EnterEmail -> enterEmail(event.email)
            is LoginScreenEvent.EnterPassword -> enterPassword(event.password)
            LoginScreenEvent.ClickLogin -> logIn()
            LoginScreenEvent.ClickSendEmail -> clickSendEmail()
        }
    }

    private fun logIn() {
        if (validateEmail(loginUiState.value.email)) {
            _loginUiState.value = loginUiState.value.copy(emailFormatError = -1)
            if (validatePassword(loginUiState.value.password)) {
                _loginUiState.value = loginUiState.value.copy(passwordFormatError = -1)
                _loginUiState.value = loginUiState.value.copy(isLoading = true)
                viewModelScope.launch {
                    val result = authenticationRepository.login(loginUiState.value.email, loginUiState.value.password)
                    _loginUiState.value = if (result) {
                        loginUiState.value.copy(isLogInSuccess = true, isLoading = false)
                    } else {
                        loginUiState.value.copy(loginError = R.string.login_screen_incorrect, isLoading = false)
                    }
                }
            } else {
                _loginUiState.value = loginUiState.value.copy(passwordFormatError = R.string.login_screen_passwordLess, isLoading = false)
            }
        } else {
            _loginUiState.value = loginUiState.value.copy(emailFormatError = R.string.login_screen_emailFormat, isLoading = false)
        }
    }

    private fun clickSendEmail() {
        if (validateEmail(loginUiState.value.bottomSheetEmail)) {
            _loginUiState.value = loginUiState.value.copy(bottomSheetEmailFormat = -1)
            viewModelScope.launch {
                try {
                    val result = authenticationRepository.forgotPassword(loginUiState.value.bottomSheetEmail)
                    _loginUiState.value = if (result) {
                        loginUiState.value.copy(bottomSheetSendState = R.string.login_screen_emailSent)
                    } else {
                        loginUiState.value.copy(bottomSheetSendState = R.string.login_screen_wrongAccount)
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                    _loginUiState.value = loginUiState.value.copy(bottomSheetSendState = R.string.login_screen_wrongAccount)
                }
            }
        } else {
            _loginUiState.value = loginUiState.value.copy(bottomSheetEmailFormat = R.string.login_screen_emailFormat)
        }
    }

    private fun openBottomSheet() {
        _loginUiState.value = loginUiState.value.copy(isBottomSheetOpen = true)
    }

    private fun onDismissBottomSheet() {
        _loginUiState.value = loginUiState.value.copy(isBottomSheetOpen = false, bottomSheetEmail = "", bottomSheetEmailFormat = -1)
    }

    private fun enterBottomSheetEmail(email: String) {
        _loginUiState.value = loginUiState.value.copy(bottomSheetEmail = email, bottomSheetEmailFormat = -1)
    }

    private fun enterEmail(email: String) {
        _loginUiState.value = loginUiState.value.copy(email = email, emailFormatError = -1)
    }

    private fun enterPassword(password: String) {
        _loginUiState.value = loginUiState.value.copy(password = password, passwordFormatError = -1)
    }

    fun clearErrors() {
        _loginUiState.value = loginUiState.value.copy(
            bottomSheetSendState = -1,
            loginError = -1,
            emailFormatError = -1,
            passwordFormatError = -1
        )
    }

    private fun validateEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validatePassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 8 && !password.contains(Regex("\\s"))
    }


    fun clearUiState(){
        _loginUiState.value = LoginUiState()
    }

}
