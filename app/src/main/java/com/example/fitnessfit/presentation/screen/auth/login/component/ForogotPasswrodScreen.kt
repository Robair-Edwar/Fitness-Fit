package com.example.fitnessfit.presentation.screen.auth.login.component

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes
import com.example.fitnessfit.presentation.screen.auth.common.AuthTextField
import com.example.fitnessfit.util.Util.isInternetAvailable
import com.example.fitnessfit.util.Util.makeToast

@Composable
fun ForgotPasswordScreen(
    onSendClick: () -> Unit,
    email: String,
    type: String,
    onEmailChange: (email: String) -> Unit,
    bottomSheetEmailError: Int,
    bottomSheetSendState: Int,
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
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = Dimens.Small4, vertical = Dimens.Medium),
            text = stringResource(id = R.string.login_screen_hintSheet),
            fontSize = Sizes.Medium2,
            lineHeight = Sizes.Medium3,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Start
        )
        AuthTextField(
            placeholder = stringResource(id = R.string.login_screen_emailSheet),
            text = email,
            type = type,
            errorState = bottomSheetEmailError
        ) { text ->
            onEmailChange(text)
        }
        Spacer(modifier = Modifier.height(Dimens.Small3))
        if (bottomSheetEmailError != -1){
            Text(
                modifier = Modifier.align(Alignment.Start),
                fontSize = Sizes.Medium,
                textAlign = TextAlign.Start,
                text = stringResource(id = bottomSheetEmailError),
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(Dimens.Medium3))
        Button(
            modifier = Modifier.height(Dimens.Large3),
            onClick = {
                if (isInternetAvailable(context)){
                    onSendClick()
                }else {
                    makeToast(context, R.string.login_screen_internet)
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
    if (bottomSheetSendState != -1){
        makeToast(context = context, data = bottomSheetSendState)
        afterClickAction()
    }
}