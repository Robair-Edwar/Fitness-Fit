package com.example.fitnessfit.presentation.screen.on_boarding.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes
import com.example.fitnessfit.presentation.screen.on_boarding.OnboardingViewModel


@Preview
@Composable
fun OnboardingScreenContentButtonsPreview() {
    OnboardingScreenContentButtons(
        choice1 = "",
        choice2 = "",
        choice3 = "",
        choice4 = "",
        selectedAnswer = 1,
        choicesNumber = 2,
        onAnswerSelected = {}
    )
}

@Composable
fun OnboardingScreenContentButtons(
    choice1: String,
    choice2: String,
    choice3: String,
    choice4: String,
    choicesNumber: Int,
    selectedAnswer: Int,
    onAnswerSelected: (answer: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = .6f)
            .padding(top = Dimens.Medium3)
    ) {
        Button(
            modifier = Modifier
                .height(Dimens.Large4)
                .fillMaxWidth()
                .padding(Dimens.Small3)
                .clip(RoundedCornerShape(50))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(50)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedAnswer == 1 )
                    MaterialTheme.colorScheme.tertiary
                else
                    MaterialTheme.colorScheme.background
            ),
            onClick = {
                onAnswerSelected(1)
            }) {
            Text(
                fontWeight = FontWeight.Bold,
                text = choice1,
                fontSize = Sizes.Medium3,
                color = if (selectedAnswer == 1)
                    MaterialTheme.colorScheme.background
                else
                    MaterialTheme.colorScheme.onBackground
            )
        }
        Button(
            modifier = Modifier
                .height(Dimens.Large4)
                .fillMaxWidth()
                .padding(Dimens.Small3)
                .clip(RoundedCornerShape(50))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(50)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedAnswer == 2 )
                    MaterialTheme.colorScheme.tertiary
                else
                    MaterialTheme.colorScheme.background
            ),
            onClick = {
                onAnswerSelected(2)
            }) {
            Text(
                fontWeight = FontWeight.Bold,
                text = choice2,
                fontSize = Sizes.Medium3,
                color = if (selectedAnswer == 2)
                    MaterialTheme.colorScheme.background
                else
                    MaterialTheme.colorScheme.onBackground
            )
        }
        Button(
            modifier = Modifier
                .height(Dimens.Large4)
                .fillMaxWidth()
                .padding(Dimens.Small3)
                .clip(RoundedCornerShape(50))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(50)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedAnswer == 3 )
                    MaterialTheme.colorScheme.tertiary
                else
                    MaterialTheme.colorScheme.background
            ),
            onClick = {
                onAnswerSelected(3)
            }) {
            Text(
                fontWeight = FontWeight.Bold,
                text = choice3,
                fontSize = Sizes.Medium3,
                color = if (selectedAnswer == 3)
                    MaterialTheme.colorScheme.background
                else
                    MaterialTheme.colorScheme.onBackground
            )
        }
        if (choicesNumber == 4) {
            Button(
                modifier = Modifier
                    .height(Dimens.Large4)
                    .fillMaxWidth()
                    .padding(Dimens.Small3)
                    .clip(RoundedCornerShape(50))
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(50)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedAnswer == 4 )
                        MaterialTheme.colorScheme.tertiary
                    else
                        MaterialTheme.colorScheme.background
                ),
                onClick = {
                    onAnswerSelected(4)
                }) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = choice4,
                    fontSize = Sizes.Medium3,
                    color = if (selectedAnswer == 4)
                        MaterialTheme.colorScheme.background
                    else
                        MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}