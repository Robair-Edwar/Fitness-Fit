package com.example.fitnessfit.presentation.screen.on_boarding.common

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
import com.example.fitnessfit.presentation.screen.on_boarding.OnboardingViewModel
import com.example.fitnessfit.util.Language
import com.example.fitnessfit.util.Util

@Preview
@Composable
fun OnboardingScreenContentPreview() {
    OnboardingScreenContent("",
        choice1 = "",
        choice2 = "",
        choice3 = "",
        choice4 = "",
        isButtonOpen = true,
        selectedAnswer = 1,
        choicesNumber = 3,
        screenNumber = 1,
        numberOfTheScreen = "2/8",
        onNextButtonClick = {},
        onBackButtonClick = {},
        onAnswerSelected = {}
    )
}

@Composable
fun OnboardingScreenContent(
    questionText: String,
    choice1: String,
    choice2: String,
    choice3: String,
    choice4: String = "",
    screenNumber: Int,
    choicesNumber: Int,
    isButtonOpen: Boolean,
    selectedAnswer: Int,
    numberOfTheScreen: String,
    onNextButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    onAnswerSelected: (answer: Int) -> Unit,
    ) {
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
                text = numberOfTheScreen,
                fontSize = Sizes.Medium4,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
        LinearProgressIndicator(
            modifier = Modifier
                .padding(vertical = Dimens.Medium)
                .align(Alignment.CenterHorizontally),
            progress = when (screenNumber) {
                2 -> .250f
                3 -> .375f
                4 -> .500f
                5 -> .625f
                6 -> .750f
                else -> {
                    0f
                }
            }
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.Large2)
                .weight(1.5f),
            fontWeight = FontWeight.Bold,
            text = questionText,
            fontSize = Sizes.Medium4,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            lineHeight = Sizes.Large
        )
        OnboardingScreenContentButtons(
            choice1 = choice1,
            choice2 = choice2,
            choice3 = choice3,
            choice4 = choice4,
            choicesNumber = choicesNumber,
            selectedAnswer = selectedAnswer,
            modifier = Modifier.weight(8f),
            onAnswerSelected = { onAnswerSelected(it) }
        )
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
            enabled = isButtonOpen
        ) {
            Text(
                text = stringResource(id = R.string.onboarding1_Next),
                textAlign = TextAlign.Center,
                fontSize = Sizes.Medium3,
            )
        }
    }
}