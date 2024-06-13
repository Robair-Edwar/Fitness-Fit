package com.example.fitnessfit.presentation.screen.meals.meal_screen

import androidx.annotation.StringRes
import com.example.fitnessfit.data.local.room.Meal

data class MealUiState(
    val isLoading: Boolean = false,
    @StringRes val error: Int? = null,
    val meal: Meal? = null
)