package com.example.fitnessfit.presentation.screen.on_boarding.fifth_onboarding

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
fun FifthOnBoardingScreenPreview() {
    FifthOnBoardingScreen(
        onNextButtonClick = { /*TODO*/ },
        onBackButtonClick = { /*TODO*/ },
        onboardingViewModel = OnboardingViewModel()
    )
}

@Composable
fun FifthOnBoardingScreen(
    onNextButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    onboardingViewModel: OnboardingViewModel,
) {
    val screenUiState by onboardingViewModel.onboardingState.collectAsState()
    OnboardingScreenContent(
        stringResource(id = R.string.onboarding5_diet_question),
        stringResource(id = R.string.onboarding5_diet_yes),
        stringResource(id = R.string.onboarding5_diet_no),
        stringResource(id = R.string.onboarding5_diet_notAlways),
        screenNumber = 5,
        choicesNumber = 3,
        numberOfTheScreen = "5/8",
        isButtonOpen = screenUiState.isEatHealthyButtonOpen,
        selectedAnswer = screenUiState.eatHealthy,
        onNextButtonClick = { onNextButtonClick() },
        onBackButtonClick = { onBackButtonClick() },
        onAnswerSelected = { choice ->
            onboardingViewModel.userEvent(OnboardingEvents.Onboarding5(choice))
        }
    )
}