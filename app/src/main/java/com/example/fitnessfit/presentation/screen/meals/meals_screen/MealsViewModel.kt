package com.example.fitnessfit.presentation.screen.meals.meals_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessfit.R
import com.example.fitnessfit.domain.repository.MealRepository
import com.example.fitnessfit.util.Language
import com.example.fitnessfit.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val mealRepository: MealRepository
):ViewModel() {

    private val _uiState = MutableStateFlow(MealsUiState())
    val uiState: StateFlow<MealsUiState> = _uiState.asStateFlow()

    fun getAllMeals(category: String){
        viewModelScope.launch {
            try {
                _uiState.value = MealsUiState(
                    isLoading = true,
                )
                if (Util.getCurrentLanguage() == Language.ENGLISH.code){
                    mealRepository.getMealByEnglishCategory(category).collect{ mealsList ->
                        _uiState.value = MealsUiState(mealsList = mealsList)
                    }
                }else {
                    mealRepository.getMealByArabicCategory(category).collect{ mealsList ->
                        _uiState.value = MealsUiState(mealsList = mealsList)
                    }
                }
            }catch (e: Exception){
                _uiState.value = MealsUiState(
                    error = R.string.mealsScreen_error
                )
            }
        }
    }

}