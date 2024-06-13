package com.example.fitnessfit.presentation.screen.on_boarding.eighth_onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import com.example.fitnessfit.presentation.screen.on_boarding.eighth_onboarding.component.NumberPicker
import com.example.fitnessfit.util.Language
import com.example.fitnessfit.util.Util

@Preview
@Composable
fun EighthOnboardingPreview() {
    EighthOnboarding({}, {}, OnboardingViewModel())
}

@Composable
fun EighthOnboarding(
    onFinishButtonClick: () -> Unit,
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
                text = "8/8",
                fontSize = Sizes.Medium4,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
        LinearProgressIndicator(
            modifier = Modifier
                .padding(vertical = Dimens.Medium)
                .align(Alignment.CenterHorizontally),
            progress = 1f
        )
        NumberPicker(
            modifier = Modifier.weight(2.6f),
            type = "age",
            range1 = (18..70).toList(),
            selectedFirstNumber = screenUiState.age,
            onFirstNumberSelected = {
                onboardingViewModel.userEvent(
                    OnboardingEvents.Onboarding8.Age(
                        it
                    )
                )
            }
        )
        Divider(
            modifier = Modifier
                .padding(Dimens.Small3)
        )
        NumberPicker(
            modifier = Modifier.weight(2.6f),
            type = "height",
            range1 = (140..220).toList(),
            selectedFirstNumber = screenUiState.tall,
            onFirstNumberSelected = {
                onboardingViewModel.userEvent(
                    OnboardingEvents.Onboarding8.Tall(
                        it
                    )
                )
            }
        )
        Divider(
            modifier = Modifier
                .padding(Dimens.Small3)
        )
        NumberPicker(
            modifier = Modifier.weight(3.8f),
            type = "weight",
            range1 = (30..150).toList(),
            selectedFirstNumber = screenUiState.weight1,
            range2 = listOf(.0f,.1f, .2f, .3f, .4f, .5f, .6f, .7f, .8f, .9f),
            selectedSecondNumber = screenUiState.weight2,
            onFirstNumberSelected = {
                 onboardingViewModel.userEvent(OnboardingEvents.Onboarding8.Weight1(it))
            },
            onSecondNumberSelected = {
                onboardingViewModel.userEvent(OnboardingEvents.Onboarding8.Weight2(it))
            }
        )
        Spacer(modifier = Modifier
            .height(Dimens.Small4)
            .weight(.1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.Large)
                .weight(1f)
                .padding(Dimens.Small3)
                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(50)),
            onClick = {
                onFinishButtonClick()
            },
            enabled = screenUiState.isPickersButtonOpen
        ) {
            Text(
                text = stringResource(id = R.string.onboarding8_Finish),
                textAlign = TextAlign.Center,
                fontSize = Sizes.Medium3,
            )
        }
    }
}