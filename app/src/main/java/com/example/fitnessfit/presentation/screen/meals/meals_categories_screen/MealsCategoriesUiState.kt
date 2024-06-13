package com.example.fitnessfit.presentation.screen.meals.meals_categories_screen

import androidx.annotation.StringRes

data class MealsCategoriesUiState(
    val isLoading: Boolean = false,
    @StringRes val error: Int? = null,
    val categoryList: List<String> = emptyList()
)