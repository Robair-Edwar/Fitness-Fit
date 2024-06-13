package com.example.fitnessfit.presentation.screen.auth.register

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnessfit.R
import com.example.fitnessfit.domain.models.ExerciseModel
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes
import com.example.fitnessfit.presentation.screen.auth.common.AuthTextField
import com.example.fitnessfit.presentation.screen.on_boarding.getUserInputData
import com.example.fitnessfit.util.Language
import com.example.fitnessfit.util.Util

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen({}, {})
}

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel<RegisterViewModel>()
) {

    val screenUiState by registerViewModel.registerUiState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                top = Dimens.Medium,
                bottom = Dimens.Medium3,
                start = Dimens.Medium3,
                end = Dimens.Medium3
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        IconButton(onClick = { onBackButtonClick() }) {
            Icon(
                modifier = Modifier.rotate(if (Util.getCurrentLanguage() == Language.ARABIC.code) 180f else 0f),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = Dimens.Medium),
            text = stringResource(id = R.string.register_screen_welcoming),
            fontSize = Sizes.Large,
            fontWeight = FontWeight.Bold,
            lineHeight = Sizes.Large2,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier.padding(bottom = Dimens.Medium),
            text = stringResource(id = R.string.register_screen_fillData),
            fontSize = Sizes.Medium2,
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Start
        )
        if (screenUiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = Dimens.Medium)
            )
        }
        AuthTextField(
            placeholder = stringResource(id = R.string.register_screen_name),
            text = screenUiState.name,
            type = "name",
            errorState = screenUiState.nameError
        ) { text ->
            registerViewModel.clickEvent(RegisterEvent.EnterName(text))
        }
        Spacer(modifier = Modifier.height(Dimens.Small3))
        if (screenUiState.nameError != -1) {
            Text(
                modifier = Modifier.padding(bottom = Dimens.Small3),
                fontSize = Sizes.Medium,
                textAlign = TextAlign.Start,
                text = stringResource(id = screenUiState.nameError),
                color = Color.Red
            )
        }
        AuthTextField(
            placeholder = stringResource(id = R.string.register_screen_email),
            text = screenUiState.email,
            type = "email",
            errorState = screenUiState.emailError
        ) { text ->
            registerViewModel.clickEvent(RegisterEvent.EnterEmail(text))
        }
        Spacer(modifier = Modifier.height(Dimens.Small3))
        if (screenUiState.emailError != -1) {
            Text(
                modifier = Modifier.padding(bottom = Dimens.Small3),
                fontSize = Sizes.Medium,
                textAlign = TextAlign.Start,
                text = stringResource(id = screenUiState.emailError),
                color = Color.Red
            )
        }
        AuthTextField(
            placeholder = stringResource(id = R.string.register_screen_password),
            text = screenUiState.password,
            type = "password",
            errorState = screenUiState.passwordError
        ) { text ->
            registerViewModel.clickEvent(RegisterEvent.EnterPassword(text))
        }
        Spacer(modifier = Modifier.height(Dimens.Small3))
        if (screenUiState.passwordError != -1) {
            Text(
                modifier = Modifier.padding(bottom = Dimens.Small3),
                fontSize = Sizes.Medium,
                textAlign = TextAlign.Start,
                text = stringResource(id = screenUiState.passwordError),
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(Dimens.Large))
        Button(
            modifier = Modifier.height(Dimens.Large3),
            onClick = {
                if (Util.isInternetAvailable(context = context)){
                    registerViewModel.clickEvent(RegisterEvent.ClickRegister)
                }else {
                    Util.makeToast(context,R.string.register_screen_internet)
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
                text = stringResource(id = R.string.register_screen_register),
                fontSize = Sizes.Medium2,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(Dimens.Medium4))
        Text(
            modifier = Modifier.padding(bottom = Dimens.Small3),
            text = stringResource(id = R.string.register_screen_hint),
            textAlign = TextAlign.Start,
            fontSize = Sizes.Medium,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = Sizes.Medium2
        )
    }
    LaunchedEffect(key1 = screenUiState.isSignedUpSuccess) {
        if (screenUiState.isSignedUpSuccess) {
            val model = ExerciseModel(context)
            val prediction = model.predict(getUserInputData())
            registerViewModel.saveUserPlan(prediction)
            onRegisterClick()
        }
    }
    if (screenUiState.registerError != -1){
        Util.makeToast(context, stringResource(id = screenUiState.registerError))
        registerViewModel.clearErrors()
    }
}