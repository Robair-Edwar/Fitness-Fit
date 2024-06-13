package com.example.fitnessfit.presentation.screen.dashboard.util

import com.example.fitnessfit.presentation.screen.dashboard.UserDashboard
import com.example.fitnessfit.presentation.screen.on_boarding.User

fun User.toUser(): UserDashboard {
    return UserDashboard(
        name = this.name,
        gender = this.gender.toInt(),
        age = this.age.toInt(),
        height = this.tall.toInt(),
        weight = this.weight,
        finished = 0,
        remain = 32,
        firstWeekProgress = 0,
        secondWeekProgress = 0,
        thirdWeekProgress = 0,
        fourthWeekProgress = 0
    )
}