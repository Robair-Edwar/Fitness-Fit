package com.example.fitnessfit.presentation.screen.on_boarding

data class OnboardingState(
    var gender:Int = 0,
    var isGenderButtonOpen: Boolean = false,
    var fitnessLevel:Int = 0,
    var isFitnessLevelButtonOpen: Boolean = false,
    var oftenDoExercise:Int = 0,
    var isOftenDoExerciseButtonOpen: Boolean = false,
    var spendExercising:Int = 0,
    var isSpendExercisingButtonOpen: Boolean = false,
    var eatHealthy:Int = 0,
    var isEatHealthyButtonOpen: Boolean = false,
    var considerHealthy:Int = 0,
    var isConsiderHealthyButtonOpen: Boolean = false,
    var selectedMotivations: MutableList<Int> = mutableListOf(),
    var isMotivationButtonOpen: Boolean = false,
    var age:Int = 0,
    var tall:Int = 0,
    var weight1:Int = 0,
    var weight2:Float = 0.0f,
    var isPickersButtonOpen: Boolean = false
)
