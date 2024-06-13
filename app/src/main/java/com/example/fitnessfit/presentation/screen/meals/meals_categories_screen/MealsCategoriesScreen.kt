package com.example.fitnessfit.presentation.screen.meals.meals_categories_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnessfit.presentation.common.bars.BottomAppBar
import com.example.fitnessfit.presentation.common.bars.BottomBarScreens
import com.example.fitnessfit.presentation.common.ErrorScreen
import com.example.fitnessfit.presentation.common.LoadingScreen
import com.example.fitnessfit.presentation.screen.meals.meals_categories_screen.component.MealsCategoryList

@Preview
@Composable
fun MealsCategoriesScreenPreview() {
    MealsCategoriesScreen({},{},{},{}, hiltViewModel<MealsCategoryViewModel>())
}

@Composable
fun MealsCategoriesScreen(
    onCategoryClick: (category: String) -> Unit,
    onWorkoutOutClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onDashboardClick: () -> Unit,
    mealsCategoryViewModel: MealsCategoryViewModel = hiltViewModel<MealsCategoryViewModel>()
    ) {
    val uiState by mealsCategoryViewModel.uiState.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                selectedBottomBarScreens = BottomBarScreens.Meals,
                onWorkoutClick = { onWorkoutOutClick() },
                onSettingsClick = {
                    onSettingsClick()
                },
                onDashboardClick = onDashboardClick
                )
        }
    ) { paddingValues ->
        when {
            uiState.isLoading -> {
                LoadingScreen(Modifier.padding(paddingValues))
            }
            uiState.categoryList.isNotEmpty() -> {
                MealsCategoryList(categories = uiState.categoryList, Modifier.padding(paddingValues)){ category ->
                    onCategoryClick(category)
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
