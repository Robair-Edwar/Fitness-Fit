package com.example.fitnessfit.presentation.screen.on_boarding

import androidx.lifecycle.ViewModel
import com.example.fitnessfit.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingViewModel: ViewModel() {
    private var _onboardingState = MutableStateFlow(OnboardingState())
    val onboardingState = _onboardingState.asStateFlow()


    fun userEvent(onboardingEvent: OnboardingEvents) {
        when (onboardingEvent) {
            is OnboardingEvents.Onboarding1 -> setOnboarding1(onboardingEvent.gender)
            is OnboardingEvents.Onboarding2 -> setOnboarding2(onboardingEvent.fitnessLevel)
            is OnboardingEvents.Onboarding3 -> setOnboarding3(onboardingEvent.oftenDoExercise)
            is OnboardingEvents.Onboarding4 -> setOnboarding4(onboardingEvent.spendExercising)
            is OnboardingEvents.Onboarding5 -> setOnboarding5(onboardingEvent.eatHealthy)
            is OnboardingEvents.Onboarding6 -> setOnboarding6(onboardingEvent.considerHealthy)
            is OnboardingEvents.Onboarding7 -> setOnboarding7(onboardingEvent.motivate)
            is OnboardingEvents.Onboarding8.Age -> setOnboarding8Age(onboardingEvent.age)
            is OnboardingEvents.Onboarding8.Tall -> setOnboarding8Tall(onboardingEvent.tall)
            is OnboardingEvents.Onboarding8.Weight1 -> setOnboarding8Weight1(onboardingEvent.weight1)
            is OnboardingEvents.Onboarding8.Weight2 -> setOnboarding8Weight2(onboardingEvent.weight2)
        }
    }

    private fun setOnboarding1(gender: Int) {
        _onboardingState.value = onboardingState.value.copy(
            gender = gender,
            isGenderButtonOpen = true
        )
        User.gender = if (gender == 1) 0f else 1f
    }

    private fun setOnboarding2(fitnessLevel: Int) {
        _onboardingState.value = onboardingState.value.copy(
            fitnessLevel = fitnessLevel,
            isFitnessLevelButtonOpen = true
        )
        User.fitnessLevel = if (fitnessLevel == 1 ) 0f else if (fitnessLevel == 2 ) 1f else 2f
    }

    private fun setOnboarding3(oftenDoExercise: Int) {
        _onboardingState.value = onboardingState.value.copy(
            oftenDoExercise = oftenDoExercise,
            isOftenDoExerciseButtonOpen = true
        )
        User.oftenDoExercise = if (oftenDoExercise == 1 ) 0f else if (oftenDoExercise == 2 ) 1f else if (oftenDoExercise == 3 ) 2f else 3f
    }

    private fun setOnboarding4(spendExercising: Int) {
        _onboardingState.value = onboardingState.value.copy(
            spendExercising = spendExercising,
            isSpendExercisingButtonOpen = true
        )
        User.spendExercising = if (spendExercising == 1 ) 0f else if (spendExercising == 2 ) 1f else if (spendExercising == 3 ) 2f else 3f
    }

    private fun setOnboarding5(eatHealthy: Int) {
        _onboardingState.value = onboardingState.value.copy(
            eatHealthy = eatHealthy,
            isEatHealthyButtonOpen = true
        )
        User.eatHealthy = if (eatHealthy == 1) 1f else if (eatHealthy == 2) 0f else .5f
    }

    private fun setOnboarding6(considerHealthy: Int) {
        _onboardingState.value = onboardingState.value.copy(
            considerHealthy = considerHealthy,
            isConsiderHealthyButtonOpen = true
        )
        User.considerHealthy = if (considerHealthy == 1) 0f else if (considerHealthy == 2 ) 1f else 2f
    }

    private fun setOnboarding7(motivate: Int) {
        val currentMotivations = onboardingState.value.selectedMotivations.toMutableList()
        if (currentMotivations.contains(motivate)) {
            currentMotivations.remove(motivate)
        } else {
            currentMotivations.add(motivate)
        }
        _onboardingState.value = onboardingState.value.copy(
            selectedMotivations = currentMotivations
        )
        User.motivate[motivate - 1] = if (User.motivate[motivate - 1] == 1) 0 else 1
        isOnboarding7ButtonEnabled()
    }

    private fun isOnboarding7ButtonEnabled() {
        _onboardingState.value = onboardingState.value.copy(
            isMotivationButtonOpen = onboardingState.value.selectedMotivations.isNotEmpty()
        )
    }

    private fun setOnboarding8Age(age: Int) {
        _onboardingState.value = onboardingState.value.copy(
            age = age
        )
        User.age = age.toFloat()
        isOnboarding8ButtonEnabled()
    }

    private fun setOnboarding8Tall(tall: Int) {
        _onboardingState.value = onboardingState.value.copy(
            tall = tall
        )
        User.tall = tall.toFloat()
        isOnboarding8ButtonEnabled()
    }

    private fun setOnboarding8Weight1(weight1: Int) {
        _onboardingState.value = onboardingState.value.copy(
            weight1 = weight1
        )
        User.weight = (onboardingState.value.weight1.toFloat() + onboardingState.value.weight2)
        isOnboarding8ButtonEnabled()
    }

    private fun setOnboarding8Weight2(weight2: Float) {
        _onboardingState.value = onboardingState.value.copy(
            weight2 = weight2
        )
        User.weight = (onboardingState.value.weight1.toFloat() + onboardingState.value.weight2)
    }

    fun isOnboarding8ButtonEnabled() {
        _onboardingState.value = onboardingState.value.copy(
            isPickersButtonOpen = onboardingState.value.age != 0 && onboardingState.value.tall != 0 && onboardingState.value.weight1 != 0
        )
    }

    fun clearUserData() {
        _onboardingState.value = OnboardingState()
    }
}