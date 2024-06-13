package com.example.fitnessfit.presentation.screen.on_boarding.sixth_onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.screen.on_boarding.OnboardingEvents
import com.example.fitnessfit.presentation.screen.on_boarding.OnboardingViewModel
import com.example.fitnessfit.presentation.screen.on_boarding.common.OnboardingScreenContent

@Preview
@Composable
fun SixthOnBoardingScreenPreview() {
    SixthOnBoardingScreen({}, {}, OnboardingViewModel())
}

@Composable
fun SixthOnBoardingScreen(
    onNextButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    onboardingViewModel: OnboardingViewModel,
) {
    val screenUiState by onboardingViewModel.onboardingState.collectAsState()
    OnboardingScreenContent(
        stringResource(id = R.string.onboarding6_healthy_question),
        stringResource(id = R.string.onboarding6_healthy_1),
        stringResource(id = R.string.onboarding6_healthy_2),
        stringResource(id = R.string.onboarding6_healthy_3),
        screenNumber = 6,
        choicesNumber = 3,
        numberOfTheScreen = "6/8",
        isButtonOpen = screenUiState.isConsiderHealthyButtonOpen,
        selectedAnswer = screenUiState.considerHealthy,
        onNextButtonClick = { onNextButtonClick() },
        onBackButtonClick = { onBackButtonClick() },
        onAnswerSelected = { choice ->
            onboardingViewModel.userEvent(OnboardingEvents.Onboarding6(choice))
        }
    )
}