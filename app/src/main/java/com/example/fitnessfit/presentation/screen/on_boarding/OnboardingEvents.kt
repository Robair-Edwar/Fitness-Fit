package com.example.fitnessfit.presentation.screen.on_boarding

sealed class OnboardingEvents{
    data class Onboarding1(var gender: Int): OnboardingEvents()
    data class Onboarding2(var fitnessLevel: Int): OnboardingEvents()
    data class Onboarding3(var oftenDoExercise: Int): OnboardingEvents()
    data class Onboarding4(var spendExercising: Int): OnboardingEvents()
    data class Onboarding5(var eatHealthy: Int): OnboardingEvents()
    data class Onboarding6(var considerHealthy: Int): OnboardingEvents()
    data class Onboarding7(var motivate: Int): OnboardingEvents()
    sealed class Onboarding8:OnboardingEvents(){
        data class Age(var age: Int):Onboarding8()
        data class Tall(var tall: Int):Onboarding8()
        data class Weight1(var weight1: Int):Onboarding8()
        data class Weight2(var weight2: Float):Onboarding8()
    }
}