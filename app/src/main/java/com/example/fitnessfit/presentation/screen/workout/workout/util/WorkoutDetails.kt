package com.example.fitnessfit.presentation.screen.workout.workout.util

data class WorkoutDetails(
    val drawableRes: Int,
    val workoutType: WorkoutType,
    val duration: Int? = null,
    val sets: Int? = null,
    val repsPerSet: Int? = null
)
