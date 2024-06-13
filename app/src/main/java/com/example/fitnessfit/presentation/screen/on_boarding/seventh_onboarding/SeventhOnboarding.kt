package com.example.fitnessfit.presentation.screen.on_boarding.seventh_onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes
import com.example.fitnessfit.presentation.screen.on_boarding.OnboardingEvents
import com.example.fitnessfit.presentation.screen.on_boarding.OnboardingViewModel
import com.example.fitnessfit.presentation.screen.on_boarding.seventh_onboarding.component.SeventhOnboardingButton
import com.example.fitnessfit.util.Language
import com.example.fitnessfit.util.Util

@Preview
@Composable
fun SeventhOnBoardingScreenPreview() {
    SeventhOnBoardingScreen({}, {}, onboardingViewModel = OnboardingViewModel())
}

@Composable
fun SeventhOnBoardingScreen(
    onNextButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    onboardingViewModel: OnboardingViewModel
) {

    val screenUiState by onboardingViewModel.onboardingState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Medium3),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.Large)
                .weight(.5f)
        ) {
            IconButton(onClick = { onBackButtonClick() }) {
                Icon(
                    modifier = Modifier.rotate(if (Util.getCurrentLanguage() == Language.ARABIC.code) 180f else 0f),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.Large),
                fontWeight = FontWeight.Bold,
                text = "7/8",
                fontSize = Sizes.Medium4,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
        LinearProgressIndicator(
            modifier = Modifier
                .padding(vertical = Dimens.Medium)
                .align(Alignment.CenterHorizontally),
            progress = .875f
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.Medium2)
                .weight(2f),
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.onboarding7_motivate_question),
            fontSize = Sizes.Medium4,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            lineHeight = Sizes.Large
        )
        Column(modifier = Modifier.weight(8.3f)) {
            SeventhOnboardingButton(
                text = R.string.onboarding7_motivate_1,
                buttonState = screenUiState.selectedMotivations.contains(1)
                ) {
                onboardingViewModel.userEvent(
                    OnboardingEvents.Onboarding7(1)
                )
            }
            SeventhOnboardingButton(
                text = R.string.onboarding7_motivate_2,
                buttonState = screenUiState.selectedMotivations.contains(2)
                ) {
                onboardingViewModel.userEvent(
                    OnboardingEvents.Onboarding7(2)
                )
            }
            SeventhOnboardingButton(
                text = R.string.onboarding7_motivate_3,
                buttonState = screenUiState.selectedMotivations.contains(3)
            ) {
                onboardingViewModel.userEvent(
                    OnboardingEvents.Onboarding7(3)
                )
            }
            SeventhOnboardingButton(
                text = R.string.onboarding7_motivate_4,
                buttonState = screenUiState.selectedMotivations.contains(4)
            ) {
                onboardingViewModel.userEvent(
                    OnboardingEvents.Onboarding7(4)
                )
            }
            SeventhOnboardingButton(
                text = R.string.onboarding7_motivate_5,
                buttonState = screenUiState.selectedMotivations.contains(5)
            ) {
                onboardingViewModel.userEvent(
                    OnboardingEvents.Onboarding7(5)
                )
            }
            SeventhOnboardingButton(
                text = R.string.onboarding7_motivate_6,
                buttonState = screenUiState.selectedMotivations.contains(6)
                ) {
                onboardingViewModel.userEvent(
                    OnboardingEvents.Onboarding7(6)
                )
            }
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
            enabled = screenUiState.isMotivationButtonOpen
        ) {
            Text(
                text = stringResource(id = R.string.onboarding1_Next),
                textAlign = TextAlign.Center,
                fontSize = Sizes.Medium3,
            )
        }
    }
}