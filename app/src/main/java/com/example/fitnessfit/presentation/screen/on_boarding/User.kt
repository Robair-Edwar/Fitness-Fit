package com.example.fitnessfit.presentation.screen.on_boarding

object User {
    var name: String = ""
    var gender: Float = 0f
    var fitnessLevel: Float = 0f
    var oftenDoExercise: Float = 0f
    var spendExercising: Float = 0f
    var eatHealthy: Float = 0f
    var considerHealthy: Float = 0f
    var motivate: MutableList<Int> = mutableListOf(0,0,0,0,0,0)
    var age: Float = 0f
    var tall: Float = 0f
    var weight: Float = 0f
}

fun getUserInputData(): FloatArray {
    return floatArrayOf(
        User.gender,
        User.age,
        User.tall,
        User.weight,
        User.fitnessLevel,
        User.oftenDoExercise,
        User.spendExercising,
        User.eatHealthy,
        User.considerHealthy
    ) + User.motivate.map { it.toFloat() }.toFloatArray()
}