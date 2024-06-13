package com.example.fitnessfit.presentation.screen.workout.home_workouts

import com.example.fitnessfit.domain.models.ExercisePrediction

data class HomeWorkoutsUiState (
    val loading: Boolean = true,
    val error: Int = -1,
    val data: ExercisePrediction? = null,
    val finished: Int = 0,
    val remain: Int = 31,
    val firstWeekProgress: Int = 0,
    val secondWeekProgress: Int = 0,
    val thirdWeekProgress: Int = 0,
    val fourthWeekProgress: Int = 0,
)