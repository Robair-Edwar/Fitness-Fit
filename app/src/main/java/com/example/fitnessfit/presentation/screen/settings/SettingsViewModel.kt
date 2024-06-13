package com.example.fitnessfit.presentation.screen.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessfit.R
import com.example.fitnessfit.domain.repository.AuthenticationRepository
import com.example.fitnessfit.domain.repository.SettingsRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val authenticationRepository: AuthenticationRepository,
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.Default) {
            fetchSettings()
            getIsEmailVerified()
        }
    }


    private val _settingsUiState = MutableStateFlow(SettingsUiState())
    var settingsUiState = _settingsUiState.asStateFlow()

    private fun getIsEmailVerified() {
        viewModelScope.launch {
            try {
                authenticationRepository.isEmailVerified().collect{ isVerified ->
                    _settingsUiState.value = settingsUiState.value.copy(
                        isEmailVerified = isVerified
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _settingsUiState.value = settingsUiState.value.copy(
                    verifyAccountError = R.string.settingsScreen_verifyError
                )
            }
        }
    }

    fun clickEvent(event: SettingsEvents) {
        when (event) {
            is SettingsEvents.ChangeLanguage -> changeLanguage(event.language)
            is SettingsEvents.ChangeTheme -> changeTheme(event.theme)
            SettingsEvents.VerifyAccount -> sendEmailVerification()
            SettingsEvents.ChangePassword -> changePassword()
            SettingsEvents.LogOut -> logOut()
            SettingsEvents.ShowDialog -> showDialog()
            SettingsEvents.DismissDialog -> dismissDialog()
            SettingsEvents.DismissBottomSheet -> dismissBottomSheet()
            is SettingsEvents.EnterOldPassword -> enterOldPassword(event.password)
            is SettingsEvents.EnterNewPassword -> enterNewPassword(event.password)
            SettingsEvents.ShowBottomSheet -> showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        _settingsUiState.value = settingsUiState.value.copy(
            isBottomSheetOpen = true
        )
    }

    private fun enterOldPassword(password: String) {
        _settingsUiState.value = settingsUiState.value.copy(
            oldPassword = password,
            oldPasswordFormatError = -1
        )
        Log.d("Update Old Password",settingsUiState.value.oldPassword)
    }

    private fun enterNewPassword(password: String) {
        _settingsUiState.value = settingsUiState.value.copy(
            newPassword = password,
            newPasswordFormatError = -1
        )
        Log.d("Update New Password",settingsUiState.value.oldPassword)
    }

    private fun showDialog() {
        _settingsUiState.value = settingsUiState.value.copy(
            isDialogOpen = true
        )
    }

    private fun dismissDialog() {
        _settingsUiState.value = settingsUiState.value.copy(
            isDialogOpen = false
        )
    }

    private fun fetchSettings() {
        viewModelScope.launch {
            launch {
                settingsRepository.getLanguage().collect { newLanguage ->
                    _settingsUiState.value = _settingsUiState.value.copy(language = newLanguage)
                }
            }
            launch {
                settingsRepository.getTheme().collect { newTheme ->
                    _settingsUiState.value = _settingsUiState.value.copy(theme = newTheme)
                }
            }
        }
    }

    private fun changeLanguage(language: String) {
        viewModelScope.launch {
            settingsRepository.setLanguage(language)
        }
    }

    private fun changeTheme(theme: Int) {
        viewModelScope.launch {
            settingsRepository.setTheme(theme)
        }
    }

    private fun sendEmailVerification() {
        viewModelScope.launch {
            try {
                _settingsUiState.value = settingsUiState.value.copy(
                    verifyAccountAlreadyVerified = -1
                )
                if (settingsUiState.value.isEmailVerified){
                    _settingsUiState.value = settingsUiState.value.copy(
                        verifyAccountAlreadyVerified = R.string.settings_verifyAccountAlreadyVerifiedToast
                    )
                }else {
                    val result = authenticationRepository.sendEmailVerification()
                    if (result) {
                        _settingsUiState.value = settingsUiState.value.copy(
                            verifyAccountEmailSent = R.string.settings_verifyAccountToast
                        )
                        Log.d("SendEmailVerification", "Email verification sent successfully")
                    }else {
                        _settingsUiState.value = settingsUiState.value.copy(
                            verifyAccountError = R.string.settingsScreen_verifyError
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("SendEmailVerification", "Failed to send email verification", e)
                _settingsUiState.value = settingsUiState.value.copy(
                    verifyAccountError = R.string.settingsScreen_verifyError
                )
            }
        }
    }


    private fun changePassword() {
        if (validatePassword(settingsUiState.value.oldPassword)){
            _settingsUiState.value = settingsUiState.value.copy(oldPasswordFormatError = -1)
            if (validatePassword(settingsUiState.value.newPassword)){
                _settingsUiState.value = settingsUiState.value.copy(newPasswordFormatError = -1)
                try {
                    viewModelScope.launch {
                        val result = authenticationRepository.changePassword(settingsUiState.value.newPassword,settingsUiState.value.oldPassword)
                        if (result){
                            _settingsUiState.value = settingsUiState.value.copy(
                                changePasswordState = R.string.settings_changePasswordDone
                            )
                            Log.d("Change Password","Password Changed Successfully")
                        }else {
                            _settingsUiState.value = settingsUiState.value.copy(
                                changePasswordState = R.string.settings_changePasswordError
                            )
                            Log.d("Change Password","Password Didn't Change")
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _settingsUiState.value = settingsUiState.value.copy(
                        changePasswordState = R.string.settings_changePasswordError
                    )
                    Log.d("Change Password","Error While Changing Password")
                }
            }else {
                _settingsUiState.value =settingsUiState.value.copy(
                    newPasswordFormatError = R.string.login_screen_passwordLess
                )
            }
        }else {
            _settingsUiState.value =settingsUiState.value.copy(
                oldPasswordFormatError = R.string.login_screen_passwordLess
            )
        }
    }

    private fun logOut() {
        try {
            viewModelScope.launch {
                val result = authenticationRepository.signOut()
                if (result) {
                    _settingsUiState.value = settingsUiState.value.copy(
                        isUserLogedOut = true
                    )
                    dismissDialog()
                } else {
                    _settingsUiState.value = settingsUiState.value.copy(
                        logOutError = R.string.settingsScreen_logOutError
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _settingsUiState.value = settingsUiState.value.copy(
                logOutError = R.string.settingsScreen_logOutError
            )
        }
    }

    private fun dismissBottomSheet(){
        _settingsUiState.value = settingsUiState.value.copy(isBottomSheetOpen = false)
        clearErrors()
    }

    fun clearErrors() {
        _settingsUiState.value = settingsUiState.value.copy(
            verifyAccountError = -1,
            logOutError = -1,
            setThemeError = -1,
            setLanguageError = -1,
            newPasswordFormatError = -1,
            oldPasswordFormatError = -1,
            changePasswordState = -1,
            newPassword = "",
            oldPassword = "",
            verifyAccountEmailSent = -1,
            verifyAccountAlreadyVerified = -1
        )
    }

    private fun validatePassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 8 && !password.contains(Regex("\\s"))
    }

    fun resetLogoutAndIsVerified(){
        _settingsUiState.value = settingsUiState.value.copy(
            isUserLogedOut = false,
            isEmailVerified = false
        )
    }
}