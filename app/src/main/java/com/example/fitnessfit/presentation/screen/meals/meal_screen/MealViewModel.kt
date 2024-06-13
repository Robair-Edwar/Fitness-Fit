package com.example.fitnessfit.presentation.screen.meals.meal_screen

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
class MealViewModel @Inject constructor(
    private val mealRepository: MealRepository
):ViewModel() {

    private val _uiState = MutableStateFlow(MealUiState())
    val uiState: StateFlow<MealUiState> = _uiState.asStateFlow()

    fun getMealById(id: Int){
        viewModelScope.launch {
            try {
                _uiState.value = MealUiState(
                    isLoading = true,
                )
                val meal = mealRepository.getMealById(id)
                _uiState.value = MealUiState(meal = meal)
            }catch (e: Exception){
                _uiState.value = MealUiState(
                    error = R.string.mealScreen_error
                )
            }
        }
    }

}