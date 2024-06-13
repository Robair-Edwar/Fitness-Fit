package com.example.fitnessfit.presentation.screen.on_boarding.eighth_onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes


@Preview
@Composable
fun NumberPickerPreview() {
    NumberPicker(
        modifier = Modifier,
        type = "weight",
        range1 = (10..99).toList(),
        selectedFirstNumber = 150,
        range2 = listOf(.0f,.1f, .2f, .3f, .4f, .5f, .6f, .7f, .8f, .9f),
        selectedSecondNumber = 0f,
        onFirstNumberSelected = {},
        onSecondNumberSelected = {}
    )
}

@Composable
fun NumberPicker(
    modifier: Modifier = Modifier,
    type: String,
    range1: List<Int>,
    selectedFirstNumber: Int = 0,
    range2: List<Float> = emptyList(),
    selectedSecondNumber: Float = 0f,
    onFirstNumberSelected: (number: Int) -> Unit,
    onSecondNumberSelected: (number: Float) -> Unit = {}
) {
    val firstListState = rememberLazyListState()
    val secondListState = rememberLazyListState()

    val selectedFirstNumberIndex by remember { mutableIntStateOf(
        when (type) {
            "age" -> if (selectedFirstNumber == 0) 7 else selectedFirstNumber - 18
            "height" -> if (selectedFirstNumber == 0) 40 else selectedFirstNumber - 140
            "weight" -> if (selectedFirstNumber == 0) 50 else selectedFirstNumber - 30
            else -> { 0 }
        }
    ) }
    val selectedSecondNumberIndex by remember {
        mutableIntStateOf(if (selectedSecondNumber <= .4f) 0 else 3 )
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.Medium4)
                .weight(1.3f),
            fontWeight = FontWeight.Bold,
            text = when (type) {
                "age" -> stringResource(id = R.string.onboarding8_age_question)
                "height" -> stringResource(id = R.string.onboarding8_height_question)
                "weight" -> stringResource(id = R.string.onboarding8_weight_question)
                else -> { "" }
            },
            fontSize = Sizes.Medium3,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.Medium4)
                .weight(1.3f),
            fontWeight = FontWeight.Bold,
            text = when (type) {
                "age" -> "$selectedFirstNumber ${ stringResource(id = R.string.onboarding8_age_answer) }"
                "height" -> "$selectedFirstNumber ${ stringResource(id = R.string.onboarding8_height_answer) }"
                "weight" -> "${selectedFirstNumber + selectedSecondNumber} ${stringResource(id = R.string.onboarding8_weight_answer)}"
                else -> { "" }
            },
            fontSize = Sizes.Medium3,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        LazyRow(
            state = firstListState,
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(20),
                    color = MaterialTheme.colorScheme.primary
                )
                .weight(2.4f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            items(range1) { number ->
                NumberPickerItem(number, number == selectedFirstNumber) {
                    onFirstNumberSelected(number)
                }
            }
        }
        if (type == "weight") {
            Spacer(modifier = Modifier.height(Dimens.Small3))
            LazyRow(
                state = secondListState,
                modifier = Modifier
                    .clip(RoundedCornerShape(20))
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(20),
                        color = MaterialTheme.colorScheme.primary
                    )
                    .weight(2.4f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                items(range2) { number ->
                    NumberPickerItem(number, number == selectedSecondNumber) {
                        onSecondNumberSelected(number)
                    }
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        firstListState.scrollToItem(selectedFirstNumberIndex)
    }
    if (type == "weight") {
        LaunchedEffect(Unit) {
            secondListState.scrollToItem(selectedSecondNumberIndex)
        }
    }
}

@Composable
fun NumberPickerItem(number: Number, isSelected: Boolean, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(Dimens.Large2)
            .padding(horizontal = Dimens.Small)
            .clip(RoundedCornerShape(20))
            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(20),
                color = MaterialTheme.colorScheme.onBackground
            )
            .clickable(onClick = onItemClick)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = number.toString(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = Sizes.Medium,
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onBackground,
        )
    }
}
