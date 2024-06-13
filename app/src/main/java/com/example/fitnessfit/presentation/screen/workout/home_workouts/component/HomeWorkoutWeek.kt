package com.example.fitnessfit.presentation.screen.workout.home_workouts.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fitnessfit.R

@Preview
@Composable
fun HomeWorkoutWeekPreview() {
    HomeWorkoutWeek(
        text = "",
        color = Color.Green,
        onClick1 = { /*TODO*/ },
        onClick2 = { /*TODO*/ },
        onClick3 = { /*TODO*/ },
        onClick5 = { /*TODO*/ },
        onClick6 = { /*TODO*/ },
        onClick7 = { /*TODO*/ },
        day1State = 1,
        day2State = 1,
        day3State = 1,
        day5State = 1,
        day6State = 1,
        day7State = 1,
        free1State = 1,
        free2State = 1,
        onFree1Click = { /*TODO*/ }) {

    }
}

@Composable
fun HomeWorkoutWeek(
    text: String,
    color: Color,
    onClick1: () -> Unit,
    onClick2: () -> Unit,
    onClick3: () -> Unit,
    onClick5: () -> Unit,
    onClick6: () -> Unit,
    onClick7: () -> Unit,
    day1State: Int,
    day2State: Int,
    day3State: Int,
    day5State: Int,
    day6State: Int,
    day7State: Int,
    free1State: Int,
    free2State: Int,
    onFree1Click: () -> Unit,
    onFree2Click: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.Extreme4)
            .padding(horizontal = Dimens.Medium)
            .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(5))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimens.Medium2, top = Dimens.Small4)
                .height(Dimens.Large2),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(Dimens.Small2)
                    .padding(vertical = Dimens.Small3)
                    .background(color = color)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Small4),
                text = text,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = Sizes.Medium2,
                textAlign = TextAlign.Start,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = Dimens.Small4, bottom = Dimens.Small4)
                .horizontalScroll(rememberScrollState())
        ) {
            DayCard(text = "1",day1State,color) {
                onClick1()
            }
            DayCard(text = "2",day2State,color) {
                onClick2()
            }
            DayCard(text = "3",day3State,color) {
                onClick3()
            }
            RestCard(text = stringResource(id = R.string.homeWorkoutScreen_rest), state = free1State, color = color) {
                onFree1Click()
            }
            DayCard(text = "5",day5State,color) {
                onClick5()
            }
            DayCard(text = "6",day6State,color) {
                onClick6()
            }
            DayCard(text = "7",day7State,color) {
                onClick7()
            }
            RestCard(text = stringResource(id = R.string.homeWorkoutScreen_rest), state = free2State, color = color) {
                onFree2Click()
            }
        }
    }
}


