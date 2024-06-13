package com.example.fitnessfit.presentation.screen.dashboard

data class UserDashboard(
    val name: String,
    val gender: Int,
    val age: Int,
    val height: Int,
    val weight: Float,
    val finished: Int,
    val remain: Int,
    val firstWeekProgress: Int,
    val secondWeekProgress: Int,
    val thirdWeekProgress: Int,
    val fourthWeekProgress: Int
) {
    constructor() : this(
        name = "",
        gender = 0,
        age = 0,
        height = 0,
        weight = 0f,
        finished = 0,
        remain = 32,
        firstWeekProgress = 0,
        secondWeekProgress = 0,
        thirdWeekProgress = 0,
        fourthWeekProgress = 0
    )
}
