package com.example.fitnessfit.presentation.screen.workout.workout

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.screen.workout.workout.component.ScreenContent
import com.example.fitnessfit.presentation.screen.workout.workout.component.WorkoutGif
import com.example.fitnessfit.presentation.screen.workout.workout.util.GetWorkoutDetails.getWorkoutDetails

@Preview
@Composable
fun WorkoutScreen5Preview() {
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WorkoutScreen5(
    workouts: List<String>,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit,
    number: Int,
    workoutViewModel: WorkoutViewModel = hiltViewModel<WorkoutViewModel>()
) {
    ScreenContent(workouts = workouts, screenNumber = 5, onNextClick = {
        workoutViewModel.updateProgress(number)
        onNextClick()
    }) {
        workoutViewModel.updateProgress(number)
        onSkipClick()
    }
}
