package com.example.fitnessfit.presentation.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.bars.BottomAppBar
import com.example.fitnessfit.presentation.common.bars.BottomBarScreens
import com.example.fitnessfit.presentation.common.bars.TopAppBar
import com.example.fitnessfit.presentation.common.bottom_sheet.BottomSheet
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.screen.settings.component.ActionButton
import com.example.fitnessfit.presentation.screen.settings.component.ChangePasswordScreen
import com.example.fitnessfit.presentation.screen.settings.component.IsEmailVerified
import com.example.fitnessfit.presentation.screen.settings.component.ThemeLanguageSwitch
import com.example.fitnessfit.presentation.screen.settings.component.WarningDialog
import com.example.fitnessfit.presentation.screen.settings.util.ChangeLanguage
import com.example.fitnessfit.presentation.screen.settings.util.Language
import com.example.fitnessfit.presentation.screen.settings.util.ReportAProblem
import com.example.fitnessfit.util.Util

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(onMealsClick = { /*TODO*/ }, onWorkoutClick = { /*TODO*/ },{},{})
}

@Composable
fun SettingsScreen(
    onMealsClick: () -> Unit,
    onWorkoutClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onDashboardClick: () -> Unit,
    settingsScreenViewModel: SettingsViewModel = hiltViewModel<SettingsViewModel>()
) {
    val screenState by settingsScreenViewModel.settingsUiState.collectAsState()
    val context = LocalContext.current
    Scaffold(
        topBar = {
             TopAppBar(
                 text = stringResource(id = R.string.settings_settings),
                 color = MaterialTheme.colorScheme.primary)
        },
        bottomBar = {
            BottomAppBar(
                selectedBottomBarScreens = BottomBarScreens.Settings,
                onWorkoutClick = { onWorkoutClick() },
                onMealsClick = { onMealsClick() },
                onDashboardClick = onDashboardClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .padding(horizontal = Dimens.Medium2),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = Dimens.Medium2
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                )
            ) {
                Column(
                    modifier = Modifier.padding(vertical = Dimens.Medium2)
                ) {
                    ThemeLanguageSwitch(
                        text = R.string.settings_arabicLanguage,
                        isChecked = Util.getCurrentLanguage() == Language.ARABIC.code
                    ) { isChecked ->
                        ChangeLanguage.changeLanguage(
                            context = context,
                            language = if (isChecked) Language.ARABIC.code else Language.ENGLISH.code
                        )
                        settingsScreenViewModel.clickEvent(
                            SettingsEvents.ChangeLanguage(if (isChecked) Language.ARABIC.code else Language.ENGLISH.code)
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimens.Medium2))
                    ThemeLanguageSwitch(
                        text = R.string.settings_darkMode,
                        isChecked = if (screenState.theme == 1) false else if (screenState.theme == 2) true else isSystemInDarkTheme()
                    ) { isChecked ->
                        settingsScreenViewModel.clickEvent(SettingsEvents.ChangeTheme(if (isChecked) 2 else 1))
                    }
                    Spacer(modifier = Modifier.height(Dimens.Medium2))
                    val context = LocalContext.current
                    ActionButton(
                        text = R.string.settings_reportAProblem, icon = Icons.Default.Warning
                    ) {
                        ReportAProblem.reportAProblem(context)
                    }
                }
            }
            Card(
                modifier = Modifier
                    .padding(Dimens.Medium2),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = Dimens.Medium2
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                )
            ) {
                Column(
                    modifier = Modifier.padding(vertical = Dimens.Medium2)
                ) {
                    IsEmailVerified(isEmailVerified = screenState.isEmailVerified)
                    Spacer(modifier = Modifier.height(Dimens.Medium2))
                    ActionButton(
                        text = R.string.settings_verifyAccount, icon = Icons.Default.AccountCircle,
                    ) {
                        settingsScreenViewModel.clickEvent(SettingsEvents.VerifyAccount)
                    }
                    Spacer(modifier = Modifier.height(Dimens.Medium2))
                    ActionButton(
                        text = R.string.settings_changePassword, icon = Icons.Default.Lock,
                    ) {
                        settingsScreenViewModel.clickEvent(SettingsEvents.ShowBottomSheet)
                    }
                    Spacer(modifier = Modifier.height(Dimens.Medium2))
                    ActionButton(
                        text = R.string.settings_logOut, icon = Icons.Default.ExitToApp,
                    ) {
                        settingsScreenViewModel.clickEvent(SettingsEvents.ShowDialog)
                    }
                }
                LaunchedEffect(screenState.verifyAccountError) {
                    if (screenState.verifyAccountError != -1){
                        Util.makeToast(context,screenState.verifyAccountError)
                        settingsScreenViewModel.clearErrors()
                    }
                }
                LaunchedEffect(screenState.verifyAccountEmailSent) {
                    if (screenState.verifyAccountEmailSent != -1){
                        Util.makeToast(context,screenState.verifyAccountEmailSent)
                        settingsScreenViewModel.clearErrors()
                    }
                }
                LaunchedEffect(screenState.logOutError) {
                    if (screenState.logOutError != -1){
                        Util.makeToast(context,screenState.logOutError)
                        settingsScreenViewModel.clearErrors()
                    }
                }
                LaunchedEffect(screenState.verifyAccountAlreadyVerified) {
                    if (screenState.verifyAccountAlreadyVerified != -1){
                        Util.makeToast(context,screenState.verifyAccountAlreadyVerified)
                        settingsScreenViewModel.clearErrors()
                    }
                }
                if (screenState.isDialogOpen) {
                    WarningDialog(
                        text = stringResource(id = R.string.settings_logOut),
                        onDismissClick = {
                            settingsScreenViewModel.clickEvent(SettingsEvents.DismissDialog)
                        },
                        onYesClick = {
                            settingsScreenViewModel.clickEvent(SettingsEvents.LogOut)
                        },
                        modifier = Modifier.padding(paddingValues)
                    )
                }
                LaunchedEffect(key1 = screenState.isUserLogedOut) {
                    if (screenState.isUserLogedOut){
                        onLogoutClick()
                        settingsScreenViewModel.resetLogoutAndIsVerified()
                    }
                }
            }
        }
    }
    if (screenState.isBottomSheetOpen) {
        BottomSheet(
            onDismiss = { settingsScreenViewModel.clickEvent(SettingsEvents.DismissBottomSheet) },
            content = {
                ChangePasswordScreen(
                    oldPassword = screenState.oldPassword,
                    oldPasswordFormatError = screenState.oldPasswordFormatError,
                    onOldPasswordChange = { oldPassword ->
                        settingsScreenViewModel.clickEvent(SettingsEvents.EnterOldPassword(oldPassword))
                    },
                    onChangePasswordClick = {
                        settingsScreenViewModel.clickEvent(SettingsEvents.ChangePassword)
                    },
                    isPasswordChanged = screenState.changePasswordState,
                    afterClickAction = {
                        settingsScreenViewModel.clearErrors()
                    },
                    newPasswordFormatError = screenState.newPasswordFormatError,
                    newPassword = screenState.newPassword,
                    onNewPasswordChange = { newPassword ->
                        settingsScreenViewModel.clickEvent(SettingsEvents.EnterNewPassword(newPassword))
                    }
                )
            }
        )
    }
}