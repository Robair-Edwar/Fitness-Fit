package com.example.fitnessfit.presentation.screen.settings.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes
import com.example.fitnessfit.presentation.screen.auth.common.AuthTextField
import com.example.fitnessfit.util.Util

@Preview
@Composable
fun ChangePasswordScreenPreview() {
    ChangePasswordScreen(
        oldPassword = "",
        oldPasswordFormatError = -1,
        onOldPasswordChange = {},
        newPassword = "",
        newPasswordFormatError = -1,
        onNewPasswordChange = {},
        onChangePasswordClick = { /*TODO*/ },
        isPasswordChanged = -1
    ) {

    }
}

@Composable
fun ChangePasswordScreen(
    oldPassword: String,
    oldPasswordFormatError: Int,
    onOldPasswordChange: (newPassword: String) -> Unit,
    newPassword: String,
    newPasswordFormatError: Int,
    onNewPasswordChange: (newPassword: String) -> Unit,
    onChangePasswordClick: () -> Unit,
    isPasswordChanged: Int,
    afterClickAction: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Medium),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthTextField(
            placeholder = stringResource(id = R.string.settings_oldPassword),
            text = oldPassword,
            type = "password",
            errorState = oldPasswordFormatError
        ) { text ->
            onOldPasswordChange(text)
        }
        if (oldPasswordFormatError != -1){
            Text(
                modifier = Modifier.align(Alignment.Start),
                fontSize = Sizes.Medium,
                textAlign = TextAlign.Start,
                text = stringResource(id = oldPasswordFormatError),
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(Dimens.Small3))
        AuthTextField(
            placeholder = stringResource(id = R.string.settings_newPassword),
            text = newPassword,
            type = "password",
            errorState = newPasswordFormatError
        ) { text ->
            onNewPasswordChange(text)
        }
        if (newPasswordFormatError != -1){
            Text(
                modifier = Modifier.align(Alignment.Start),
                fontSize = Sizes.Medium,
                textAlign = TextAlign.Start,
                text = stringResource(id = newPasswordFormatError),
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(Dimens.Small3))
        Spacer(modifier = Modifier.height(Dimens.Medium3))
        Button(
            modifier = Modifier.height(Dimens.Large3),
            onClick = {
                if (Util.isInternetAvailable(context)){
                    onChangePasswordClick()
                }else {
                    Util.makeToast(context, R.string.login_screen_internet)
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
                text = stringResource(id = R.string.login_screen_sendEmailSheet),
                fontSize = Sizes.Medium2,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
    LaunchedEffect(isPasswordChanged) {
        if (isPasswordChanged != -1){
            Util.makeToast(context = context, data = isPasswordChanged)
        }
        afterClickAction()
    }
}
