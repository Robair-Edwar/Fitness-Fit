package com.example.fitnessfit.presentation.screen.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes
import com.example.fitnessfit.presentation.common.bottom_sheet.BottomSheet
import com.example.fitnessfit.presentation.screen.auth.common.AuthTextField
import com.example.fitnessfit.presentation.screen.auth.login.component.ForgotPasswordScreen
import com.example.fitnessfit.util.Util
import kotlinx.coroutines.launch

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onRegisterClick = { /*TODO*/ },
        onLogInClick = {},
        loginViewModel = hiltViewModel<LoginViewModel>()
    )
}

@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    onLogInClick: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel<LoginViewModel>()
) {
    val loginUiState by loginViewModel.loginUiState.collectAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                top = Dimens.Large3,
                bottom = Dimens.Medium3,
                start = Dimens.Medium3,
                end = Dimens.Medium3
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = Dimens.Large),
            text = stringResource(id = R.string.login_screen_welcoming),
            fontSize = Sizes.Large,
            fontWeight = FontWeight.Bold,
            lineHeight = Sizes.Large3,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier.padding(bottom = Dimens.Large),
            text = stringResource(id = R.string.login_screen_fillData),
            fontSize = Sizes.Medium2,
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Start
        )
        if (loginUiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = Dimens.Medium)
            )
        }
        AuthTextField(
            stringResource(id = R.string.login_screen_email),
            text = loginUiState.email,
            type = "email",
            errorState = loginUiState.emailFormatError
        ) { text ->
            loginViewModel.clickEvent(LoginScreenEvent.EnterEmail(text))
        }
        Spacer(modifier = Modifier.height(Dimens.Small3))
        if (loginUiState.emailFormatError != -1) {
            Text(
                fontSize = Sizes.Medium,
                textAlign = TextAlign.Start,
                text = stringResource(id = loginUiState.emailFormatError),
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(Dimens.Medium))
        AuthTextField(
            stringResource(id = R.string.login_screen_password),
            text = loginUiState.password,
            type = "password",
            errorState = loginUiState.passwordFormatError
        ) { text ->
            loginViewModel.clickEvent(LoginScreenEvent.EnterPassword(text))
        }
        Spacer(modifier = Modifier.height(Dimens.Small3))
        if (loginUiState.passwordFormatError != -1) {
            Text(
                fontSize = Sizes.Medium,
                textAlign = TextAlign.Start,
                text = stringResource(id = loginUiState.passwordFormatError),
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(Dimens.Large))
        Button(
            modifier = Modifier.height(Dimens.Large3),
            onClick = {
                if(Util.isInternetAvailable(context)){
                    loginViewModel.clickEvent(LoginScreenEvent.ClickLogin)
                }else {
                    Util.makeToast(context,R.string.login_screen_internet)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary),
                text = stringResource(id = R.string.login_screen_login),
                fontSize = Sizes.Medium2,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
        }
        Text(
            modifier = Modifier
                .padding(top = Dimens.Small3)
                .clickable {
                    scope.launch {
                        loginViewModel.clickEvent(LoginScreenEvent.ClickForgotPassword)
                    }
                },
            text = stringResource(id = R.string.login_screen_forgotPassword),
            textAlign = TextAlign.Start,
            fontSize = Sizes.Medium2,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary,
            textDecoration = TextDecoration.Underline,
        )
        Spacer(modifier = Modifier.height(Dimens.Large))
        Text(
            modifier = Modifier.padding(bottom = Dimens.Small3),
            text = stringResource(id = R.string.login_screen_haveNotAccount),
            textAlign = TextAlign.Start,
            fontSize = Sizes.Medium3,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            modifier = Modifier
                .padding()
                .clickable {
                    onRegisterClick()
                },
            text = stringResource(id = R.string.login_screen_register),
            textAlign = TextAlign.Start,
            fontSize = Sizes.Medium3,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary,
            textDecoration = TextDecoration.Underline,
        )
    }
    if (loginUiState.loginError != -1){
        Util.makeToast(context, stringResource(id = loginUiState.loginError))
        loginViewModel.clearErrors()
    }
    LaunchedEffect(key1 = loginUiState.isLogInSuccess) {
        if (loginUiState.isLogInSuccess){
            onLogInClick()
            loginViewModel.clearUiState()
        }
    }
    if (loginUiState.isBottomSheetOpen) {
        BottomSheet(
            onDismiss = {
                scope.launch {
                    loginViewModel.clickEvent(LoginScreenEvent.DismissBottomSheet)
                }
            },
            content = {
                ForgotPasswordScreen(
                    onSendClick = {
                        loginViewModel.clickEvent(LoginScreenEvent.ClickSendEmail)
                    },
                    email = loginUiState.bottomSheetEmail,
                    type = "email",
                    onEmailChange = { email ->
                        loginViewModel.clickEvent(LoginScreenEvent.EnterBottomSheetEmail(email))
                    },
                    bottomSheetEmailError = loginUiState.bottomSheetEmailFormat,
                    bottomSheetSendState = loginUiState.bottomSheetSendState,
                    afterClickAction = {
                        loginViewModel.clearErrors()
                    }
                )
            }
        )
    }
}
