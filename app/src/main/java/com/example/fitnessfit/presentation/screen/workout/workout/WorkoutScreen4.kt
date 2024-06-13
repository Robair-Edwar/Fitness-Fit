package com.example.fitnessfit.presentation.screen.workout.workout

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.screen.workout.workout.component.ScreenContent
import com.example.fitnessfit.presentation.screen.workout.workout.component.WorkoutGif
import com.example.fitnessfit.presentation.screen.workout.workout.util.GetWorkoutDetails.getWorkoutDetails

@Preview
@Composable
fun WorkoutScreen4Preview() {
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WorkoutScreen4(
    workouts: List<String>,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit,
) {
    ScreenContent(workouts = workouts, screenNumber = 4, onNextClick = { onNextClick() }) {
        onSkipClick()
    }
}
