package com.example.fitnessfit.presentation.screen.workout.home_workouts.component

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessfit.R
import com.example.fitnessfit.domain.models.ExercisePrediction
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.screen.dashboard.UserDashboard

@Preview
@Composable
fun HomeWorkoutsScreenContentPreview() {
}

@Composable
fun HomeWorkoutsScreenContent(
    finished: Int,
    onClickDay: (number: Int) -> Unit,
    onClickRest: (number: Int) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(Dimens.Medium2))
        HomeWorkoutWeek(
            text = stringResource(id = R.string.homeWorkoutScreen_week1),
            color = Color.Cyan,
            onClick1 = { onClickDay(1) },
            onClick2 = { onClickDay(2) },
            onClick3 = { onClickDay(3) },
            onClick5 = { onClickDay(4) },
            onClick6 = { onClickDay(5) },
            onClick7 = { onClickDay(6) },
            day1State = getDayState(1,finished),
            day2State = getDayState(2,finished),
            day3State = getDayState(3,finished),
            day5State = getDayState(5,finished),
            day6State = getDayState(6,finished),
            day7State = getDayState(7,finished),
            free1State = getDayState(4,finished),
            free2State = getDayState(8,finished),
            onFree1Click = { onClickRest(4) },
            onFree2Click = { onClickRest(8) })
        Spacer(modifier = Modifier.height(Dimens.Medium2))
        HomeWorkoutWeek(
            text = stringResource(id = R.string.homeWorkoutScreen_week2),
            color = Color.Green,
            onClick1 = { onClickDay(7) },
            onClick2 = { onClickDay(8) },
            onClick3 = { onClickDay(9) },
            onClick5 = { onClickDay(10) },
            onClick6 = { onClickDay(11) },
            onClick7 = { onClickDay(12) },
            day1State = getDayState(9,finished),
            day2State = getDayState(10,finished),
            day3State = getDayState(11,finished),
            day5State = getDayState(13,finished),
            day6State = getDayState(14,finished),
            day7State = getDayState(15,finished),
            free1State = getDayState(12,finished),
            free2State = getDayState(16,finished),
            onFree1Click = { onClickRest(12) },
            onFree2Click = { onClickRest(16) })
        Spacer(modifier = Modifier.height(Dimens.Medium2))
        HomeWorkoutWeek(
            text = stringResource(id = R.string.homeWorkoutScreen_week3),
            color = Color.Yellow,
            onClick1 = { onClickDay(13) },
            onClick2 = { onClickDay(14) },
            onClick3 = { onClickDay(15) },
            onClick5 = { onClickDay(16) },
            onClick6 = { onClickDay(17) },
            onClick7 = { onClickDay(18) },
            day1State = getDayState(17,finished),
            day2State = getDayState(18,finished),
            day3State = getDayState(19,finished),
            day5State = getDayState(21,finished),
            day6State = getDayState(22,finished),
            day7State = getDayState(23,finished),
            free1State = getDayState(20,finished),
            free2State = getDayState(24,finished),
            onFree1Click = { onClickRest(20) },
            onFree2Click = { onClickRest(24) })
        Spacer(modifier = Modifier.height(Dimens.Medium2))
        HomeWorkoutWeek(
            text = stringResource(id = R.string.homeWorkoutScreen_week4),
            color = Color.Red,
            onClick1 = { onClickDay(19) },
            onClick2 = { onClickDay(20) },
            onClick3 = { onClickDay(21) },
            onClick5 = { onClickDay(22) },
            onClick6 = { onClickDay(23) },
            onClick7 = { onClickDay(24) },
            day1State = getDayState(25,finished),
            day2State = getDayState(26,finished),
            day3State = getDayState(27,finished),
            day5State = getDayState(29,finished),
            day6State = getDayState(30,finished),
            day7State = getDayState(31,finished),
            free1State = getDayState(28,finished),
            free2State = getDayState(32,finished),
            onFree1Click = { onClickRest(28) },
            onFree2Click = { onClickRest(32) })
        Spacer(modifier = Modifier.height(Dimens.Medium2))
    }
}

fun getDayState(number: Int,finished: Int): Int{
    return if (finished >= number) 1 else if (finished+1 == number) 3 else 2
}