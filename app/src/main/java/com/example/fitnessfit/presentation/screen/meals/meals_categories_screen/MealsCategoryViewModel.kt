package com.example.fitnessfit.presentation.screen.meals.meals_categories_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessfit.R
import com.example.fitnessfit.data.local.room.Meal
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
class MealsCategoryViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MealsCategoriesUiState())
    val uiState: StateFlow<MealsCategoriesUiState> = _uiState.asStateFlow()

    init {
        getAllCategories()
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            try {
                _uiState.value = MealsCategoriesUiState(isLoading = true)
                val languageCode = Util.getCurrentLanguage()
                if (languageCode == Language.ENGLISH.code) {
                    mealRepository.getAllEnglishCategories().collect { categoryList ->
                        _uiState.value = MealsCategoriesUiState(categoryList = categoryList)
                    }
                } else {
                    mealRepository.getAllArabicCategories().collect { categoryList ->
                        _uiState.value = MealsCategoriesUiState(categoryList = categoryList)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = MealsCategoriesUiState(
                    error = R.string.mealsCategoryScreen_error
                )
            }
        }
    }

    fun insertMeal(meal: Meal){
        viewModelScope.launch {
            mealRepository.insertMeal(meal)
        }
    }
}