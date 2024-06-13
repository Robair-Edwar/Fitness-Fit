package com.example.fitnessfit.presentation.screen.on_boarding.second_onboarding

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
fun SecondOnBoardingScreenPreview() {
    SecondOnBoardingScreen({}, {}, OnboardingViewModel())
}

@Composable
fun SecondOnBoardingScreen(
    onNextButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    onboardingViewModel: OnboardingViewModel,
) {
    val screenUiState by onboardingViewModel.onboardingState.collectAsState()
    OnboardingScreenContent(
        questionText = stringResource(id = R.string.onboarding2_level_question),
        choice1 = stringResource(id = R.string.onboarding2_level_unfit),
        choice2 =  stringResource(id = R.string.onboarding2_level_average),
        choice3 =  stringResource(id = R.string.onboarding2_level_good),
        screenNumber = 2,
        choicesNumber = 3,
        numberOfTheScreen = "2/8",
        isButtonOpen = screenUiState.isFitnessLevelButtonOpen,
        selectedAnswer = screenUiState.fitnessLevel,
        onNextButtonClick = { onNextButtonClick() },
        onBackButtonClick = { onBackButtonClick() },
        onAnswerSelected = { choice ->
            onboardingViewModel.userEvent(OnboardingEvents.Onboarding2(choice))
        }
    )
}