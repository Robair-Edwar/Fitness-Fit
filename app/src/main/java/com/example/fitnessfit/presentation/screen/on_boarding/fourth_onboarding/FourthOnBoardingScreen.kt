package com.example.fitnessfit.presentation.screen.on_boarding.fourth_onboarding

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
fun FourthOnBoardingScreenPreview() {
    FourthOnBoardingScreen({},{},OnboardingViewModel())
}

@Composable
fun FourthOnBoardingScreen(
    onNextButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    onboardingViewModel: OnboardingViewModel,
) {
    val screenUiState by onboardingViewModel.onboardingState.collectAsState()
    OnboardingScreenContent(
        stringResource(id = R.string.onboarding4_spend_question),
        stringResource(id = R.string.onboarding4_spend_donot),
        stringResource(id = R.string.onboarding4_spend_30minutes),
        stringResource(id = R.string.onboarding4_spend_1hour),
        stringResource(id = R.string.onboarding4_spend_2hours),
        screenNumber = 4,
        choicesNumber = 4,
        numberOfTheScreen = "4/8",
        isButtonOpen = screenUiState.isSpendExercisingButtonOpen,
        selectedAnswer = screenUiState.spendExercising,
        onNextButtonClick = { onNextButtonClick() },
        onBackButtonClick = { onBackButtonClick() },
        onAnswerSelected = { choice ->
            onboardingViewModel.userEvent(OnboardingEvents.Onboarding4(choice))
        }
    )
}