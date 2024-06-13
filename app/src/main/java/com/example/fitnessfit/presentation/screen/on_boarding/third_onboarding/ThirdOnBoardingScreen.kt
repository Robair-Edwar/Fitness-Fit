package com.example.fitnessfit.presentation.screen.on_boarding.third_onboarding

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
fun ThirdOnBoardingScreenPreview() {
    ThirdOnBoardingScreen({}, {}, OnboardingViewModel())
}

@Composable
fun ThirdOnBoardingScreen(
    onNextButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    onboardingViewModel: OnboardingViewModel,
) {
    val screenUiState by onboardingViewModel.onboardingState.collectAsState()
    OnboardingScreenContent(
        stringResource(id = R.string.onboarding3_days_question),
        stringResource(id = R.string.onboarding3_days_never),
        stringResource(id = R.string.onboarding3_days_1to2),
        stringResource(id = R.string.onboarding3_days_3to4),
        stringResource(id = R.string.onboarding3_days_everyday),
        screenNumber = 3,
        choicesNumber = 4,
        numberOfTheScreen = "3/8",
        isButtonOpen = screenUiState.isOftenDoExerciseButtonOpen,
        selectedAnswer = screenUiState.oftenDoExercise,
        onNextButtonClick = { onNextButtonClick() },
        onBackButtonClick = { onBackButtonClick() },
        onAnswerSelected = { choice ->
            onboardingViewModel.userEvent(OnboardingEvents.Onboarding3(choice))
        }
    )
}