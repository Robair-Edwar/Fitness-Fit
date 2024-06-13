package com.example.fitnessfit.presentation.screen.meals.meals_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.data.local.room.Meal
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.screen.meals.common.MovingRainbowText
import com.example.fitnessfit.util.Language
import com.example.fitnessfit.util.Util

@Preview
@Composable
fun MealsItemListPreview() {
    MealsItemList(emptyList()){}
}

@Composable
fun MealsItemList(mealsList: List<Meal>,modifier : Modifier = Modifier, onMealClick: (id: Int) -> Unit) {

    Column(modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(Dimens.Small4))
        MovingRainbowText(if (Util.getCurrentLanguage() == Language.ENGLISH.code ) mealsList[0].englishCategory else mealsList[0].arabicCategory)
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.Medium),
            verticalArrangement = Arrangement.spacedBy(Dimens.Medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items(mealsList){ meal ->
                MealsItem(meal = meal) {
                    onMealClick(meal.id)
                }
            }
        }
    }
}

