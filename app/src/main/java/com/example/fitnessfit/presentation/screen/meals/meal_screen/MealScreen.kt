package com.example.fitnessfit.presentation.screen.meals.meal_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.Share
import com.example.fitnessfit.presentation.common.ErrorScreen
import com.example.fitnessfit.presentation.common.LoadingScreen
import com.example.fitnessfit.presentation.screen.meals.meal_screen.component.MealItem

@Preview
@Composable
fun MealScreenPreview() {
    MealScreen(1)
}

@Composable
fun MealScreen(
    mealId: Int,
    mealViewModel: MealViewModel = hiltViewModel<MealViewModel>()
) {

    val uiState by mealViewModel.uiState.collectAsState()

    LaunchedEffect(mealId) {
        mealViewModel.getMealById(mealId)
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        when {
            uiState.isLoading -> {
                LoadingScreen(Modifier.padding(paddingValues))
            }
            uiState.meal != null -> {
                Share(text = stringResource(id = R.string.mealScreen_share)) {
                    MealItem(meal = uiState.meal!!, modifier = Modifier.padding(paddingValues))
                }
            }
            uiState.error != null -> {
                ErrorScreen(
                    errorMessage = stringResource(id = uiState.error!!),
                    Modifier.padding(paddingValues)
                )
            }
        }
    }
}
