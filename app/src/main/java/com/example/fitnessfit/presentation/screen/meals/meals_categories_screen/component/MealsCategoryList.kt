package com.example.fitnessfit.presentation.screen.meals.meals_categories_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.screen.meals.common.MovingRainbowText

@Preview
@Composable
fun MealsCategoryListPreview() {
    MealsCategoryList(emptyList()) {}
}

@Composable
fun MealsCategoryList(
    categories: List<String>,
    modifier: Modifier = Modifier,
    onCategoryClick: (category: String) -> Unit
) {
    Column(modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(Dimens.Medium2))
        MovingRainbowText(text = stringResource(id = R.string.mealsCategoryScreen_more))
        Spacer(modifier = Modifier.height(Dimens.Small2))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(Dimens.Medium),
            verticalArrangement = Arrangement.spacedBy(Dimens.Medium),
            horizontalArrangement = Arrangement.spacedBy(Dimens.Medium)
        ) {
            items(categories) { category ->
                MealsCategoryItem(category = category){
                    onCategoryClick(category)
                }
            }
        }
    }
}


