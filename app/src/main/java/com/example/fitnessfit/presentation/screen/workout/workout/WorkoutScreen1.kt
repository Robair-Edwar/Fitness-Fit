package com.example.fitnessfit.presentation.screen.workout.workout

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes
import com.example.fitnessfit.presentation.screen.workout.workout.component.ScreenContent
import com.example.fitnessfit.presentation.screen.workout.workout.component.SecondsCounter
import com.example.fitnessfit.presentation.screen.workout.workout.component.WorkoutGif
import com.example.fitnessfit.presentation.screen.workout.workout.util.GetWorkoutDetails.getWorkoutDetails
import com.example.fitnessfit.presentation.screen.workout.workout.util.WorkoutType

@RequiresApi(Build.VERSION_CODES.O)
@Preview()
@Composable
fun WorkoutScreen1Preview() {
    WorkoutScreen1(listOf("Arm Circles"), {}, {})
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WorkoutScreen1(
    workouts: List<String>,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit,
) {
    ScreenContent(workouts = workouts, screenNumber = 1, onNextClick = { onNextClick() }) {
        onSkipClick()
    }
}
