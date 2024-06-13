package com.example.fitnessfit.presentation.screen.on_boarding.first_onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes
import com.example.fitnessfit.presentation.screen.on_boarding.OnboardingEvents
import com.example.fitnessfit.presentation.screen.on_boarding.OnboardingViewModel
import com.example.fitnessfit.presentation.screen.on_boarding.first_onboarding.component.ChooseGender

@Preview
@Composable
fun FirstOnBoardingScreenPreview() {
    FirstOnBoardingScreen({}, {}, OnboardingViewModel())
}

@Composable
fun FirstOnBoardingScreen(
    onHaveAccountClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    onboardingViewModel: OnboardingViewModel
) {

    val onboardingState by onboardingViewModel.onboardingState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Medium3),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.Large)
                .weight(.5f),
            fontWeight = FontWeight.Bold,
            text = "1/8",
            fontSize = Sizes.Medium4,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        LinearProgressIndicator(
            progress = {
                .125f
            },
            modifier = Modifier
                .padding(vertical = Dimens.Medium)
                .align(Alignment.CenterHorizontally),
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.Large)
                .weight(1f),
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.onboarding1_gender_question),
            fontSize = Sizes.Medium4,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        ChooseGender(
            modifier = Modifier.weight(7.5f),
            gender = onboardingState.gender
        ) { gender ->
            onboardingViewModel.userEvent(OnboardingEvents.Onboarding1(gender))
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.Large)
                .weight(1f)
                .padding(Dimens.Small3)
                .background(MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(50)),
            onClick = {
                onHaveAccountClick()
            }
        ) {
            Text(
                text = stringResource(id = R.string.onboarding1_screen_haveAccount),
                textAlign = TextAlign.Center,
                fontSize = Sizes.Medium2,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.Large)
                .weight(1f)
                .padding(Dimens.Small3)
                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(50)),
            onClick = {
                onNextButtonClick()
            },
            enabled = onboardingState.isGenderButtonOpen
        ) {
            Text(
                text = stringResource(id = R.string.onboarding1_Next),
                textAlign = TextAlign.Center,
                fontSize = Sizes.Medium3
            )
        }
    }
}