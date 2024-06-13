package com.example.fitnessfit.presentation.screen.meals.meals_screen

import androidx.annotation.StringRes
import com.example.fitnessfit.data.local.room.Meal

data class MealsUiState(
    val isLoading: Boolean = false,
    @StringRes val error: Int? = null,
    val mealsList: List<Meal> = emptyList()
)