package com.example.fitnessfit.presentation.screen.dashboard

import androidx.annotation.StringRes
import com.example.fitnessfit.R

data class DashboardUiState(
    val loading:Boolean = false,
    @StringRes val error: Int = -1,
    val name: String = "",
    @StringRes val gender: Int = 0,
    val age: Int = 0,
    val height: Int = 0,
    val weight: Float = 0f,
    val finished: Int = 0,
    val remain: Int = 0,
    val firstWeekProgress: Int = 0,
    val secondWeekProgress: Int = 0,
    val thirdWeekProgress: Int = 0,
    val fourthWeekProgress: Int = 0,
)