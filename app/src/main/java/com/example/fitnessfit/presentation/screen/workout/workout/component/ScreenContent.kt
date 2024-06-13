package com.example.fitnessfit.presentation.screen.workout.workout.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.fitnessfit.presentation.screen.workout.workout.util.GetWorkoutDetails
import com.example.fitnessfit.presentation.screen.workout.workout.util.WorkoutType

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ScreenContentPreview() {
    ScreenContent(emptyList(),1,{},{})
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenContent(
    workouts: List<String>,
    screenNumber: Int,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit,
) {
    val workoutType = GetWorkoutDetails.getWorkoutDetails(workouts[screenNumber-1])!!.workoutType
    if (workoutType == WorkoutType.TIME){
        DurationWorkout(workouts = workouts, screenNumber, onNextClick = { onNextClick() }) {
            onSkipClick()
        }
    }else {
        SetsWorkout(workouts = workouts, screenNumber, onNextClick = { onNextClick() })
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DurationWorkout(
    workouts: List<String>,
    screenNumber: Int,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Medium2),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val workoutDetails = GetWorkoutDetails.getWorkoutDetails(workouts[screenNumber-1])
        var isStartButtonVisible by remember { mutableStateOf(true) }
        var isNextButtonVisible by remember { mutableStateOf(false) }
        var isSkipButtonVisible by remember { mutableStateOf(true) }
        var isCountdownRunning by remember { mutableStateOf(false) }
        var restState by remember { mutableStateOf(false) }

        if (workoutDetails != null) {
            WorkoutGif(workoutDetails.drawableRes, Modifier)
            Spacer(modifier = Modifier.height(Dimens.Medium2))
            Text(
                fontSize = Sizes.Large,
                text = if (!restState) workouts[screenNumber-1] else stringResource(id = R.string.workoutScreen1_rest),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(Dimens.Medium2))
            Text(
                fontSize = Sizes.Large,
                text = "${if (!restState) workoutDetails.duration else 30} ${stringResource(
                    id = R.string.workoutScreen1_sec
                )}",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(Dimens.Large2))

            if (isCountdownRunning) {
                workoutDetails.duration?.let {
                    SecondsCounter(startingNumber = if (!restState) it else 30, onFinish = {
                        isNextButtonVisible = true
                        isCountdownRunning = false
                        isSkipButtonVisible = false
                    })
                }
            }
            Spacer(modifier = Modifier.height(Dimens.Large4))
            if (isStartButtonVisible) {
                Button(
                    modifier = Modifier
                        .width(Dimens.Extreme3),
                    onClick = {
                        isStartButtonVisible = false
                        isCountdownRunning = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        fontSize = Sizes.Medium2,
                        text = stringResource(id = R.string.workoutScreen1_start),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.Medium2))
            }
            if (isNextButtonVisible) {
                Button(
                    modifier = Modifier
                        .width(Dimens.Extreme3),
                    onClick = {
                        if (!restState){
                            isSkipButtonVisible = true
                            restState = true
                        }else {
                            onNextClick()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        fontSize = Sizes.Medium2,
                        text = stringResource(id = R.string.workoutScreen1_next),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.Medium2))
            }
            if (isSkipButtonVisible){
                Button(
                    modifier = Modifier
                        .width(Dimens.Extreme3),
                    onClick = {
                        if (!restState){
                            isStartButtonVisible = true
                            restState = true
                            isCountdownRunning = false
                        }else {
                            onSkipClick()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        fontSize = Sizes.Medium2,
                        text = stringResource(id = R.string.workoutScreen1_skip),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetsWorkout(
    workouts: List<String>,
    screenNumber: Int,
    onNextClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Medium2),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val workoutDetails = GetWorkoutDetails.getWorkoutDetails(workouts[screenNumber - 1])
        var currentSet by remember { mutableStateOf(1) }
        var isResting by remember { mutableStateOf(false) }
        var isCountdownRunning by remember { mutableStateOf(false) }
        var isStartButtonVisible by remember { mutableStateOf(false) }

        if (workoutDetails != null) {
            WorkoutGif(workoutDetails.drawableRes, Modifier)
            Spacer(modifier = Modifier.height(Dimens.Large4))
            Text(
                fontSize = Sizes.Large,
                text = if (isResting) stringResource(id = R.string.workoutScreen1_rest) else workouts[screenNumber - 1],
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(Dimens.Medium2))
            if (!isResting) {
                Text(
                    fontSize = Sizes.Large,
                    text = "${stringResource(id = R.string.workoutScreen1_setNumber)} $currentSet",
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(Dimens.Medium))
                Text(
                    fontSize = Sizes.Large,
                    text = "X${workoutDetails.repsPerSet}",
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(Dimens.Medium2))
                Button(
                    modifier = Modifier
                        .width(Dimens.Extreme3),
                    onClick = {
                        if (currentSet < workoutDetails.sets!!) {
                            isResting = true
                            isStartButtonVisible = true
                        } else {
                            onNextClick()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        fontSize = Sizes.Medium2,
                        text = stringResource(id = R.string.workoutScreen1_done),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            if (isCountdownRunning) {
                SecondsCounter(startingNumber = 30) {
                    isResting = false
                    isStartButtonVisible = false
                    isCountdownRunning = false
                    if (currentSet < workoutDetails.sets!!) {
                        currentSet += 1
                    }
                }
            }
            Spacer(modifier = Modifier.height(Dimens.Medium2))
            if (isResting && isStartButtonVisible) {
                Text(
                    fontSize = Sizes.Large,
                    text = "30 ${stringResource(id = R.string.workoutScreen1_sec)}",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(Dimens.Medium2))
                Button(
                    modifier = Modifier
                        .width(Dimens.Extreme3),
                    onClick = {
                        isStartButtonVisible = false
                        isCountdownRunning = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        fontSize = Sizes.Medium2,
                        text = stringResource(id = R.string.workoutScreen1_start),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.Medium2))
            }
            if (isResting) {
                Button(
                    modifier = Modifier
                        .width(Dimens.Extreme3),
                    onClick = {
                        isResting = false
                        isCountdownRunning = false
                        if (currentSet < workoutDetails.sets!!) {
                            currentSet += 1
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        fontSize = Sizes.Medium2,
                        text = stringResource(id = R.string.workoutScreen1_skip),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

