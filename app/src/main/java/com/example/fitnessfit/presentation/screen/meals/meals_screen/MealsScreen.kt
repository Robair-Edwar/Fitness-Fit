package com.example.fitnessfit.presentation.screen.meals.meals_screen

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
import com.example.fitnessfit.presentation.common.ErrorScreen
import com.example.fitnessfit.presentation.common.LoadingScreen
import com.example.fitnessfit.presentation.screen.meals.meals_screen.component.MealsItemList

@Preview
@Composable
fun MealsScreenPreview() {
    MealsScreen(""){}
}

@Composable
fun MealsScreen(
    category: String,
    mealsViewModel: MealsViewModel = hiltViewModel<MealsViewModel>(),
    onMealClick: (id: Int) -> Unit
    ) {

    val uiState by mealsViewModel.uiState.collectAsState()

    LaunchedEffect(category) {
        mealsViewModel.getAllMeals(category)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        when {
            uiState.isLoading -> {
                LoadingScreen(Modifier.padding(paddingValues))
            }
            uiState.mealsList.isNotEmpty() -> {
                MealsItemList(mealsList = uiState.mealsList, modifier = Modifier.padding(paddingValues)) { id ->
                    onMealClick(id)
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