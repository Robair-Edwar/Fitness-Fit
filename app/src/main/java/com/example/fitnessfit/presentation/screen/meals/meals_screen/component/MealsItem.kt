package com.example.fitnessfit.presentation.screen.meals.meals_screen.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.data.local.room.Meal
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.screen.meals.util.ConvertToImage
import com.example.fitnessfit.presentation.screen.meals.util.GetNumberFromString
import com.example.fitnessfit.util.Language
import com.example.fitnessfit.util.Util

val meal = Meal(
    id = 50,
    arabicName = "اسم الوجبة بالعربية",
    englishName = "Meal Name in English",
    arabicCategory = "تصنيف الوجبة بالعربية",
    englishCategory = "Meal Category in English",
    arabicDescription = "وصف الوجبة بالعربية",
    englishDescription = "Meal Description in English",
    arabicIngredients = "المكونات بالعربية",
    englishIngredients = "Ingredients in English",
    arabicInstructions = "تعليمات التحضير بالعربية",
    englishInstructions = "Preparation Instructions in English",
    protein = "15",
    calories = "15",
    image = "50"
)

@Preview
@Composable
fun MealsItemPreview() {
    MealsItem(meal){}
}

@Composable
fun MealsItem(meal: Meal, onMealClick: () -> Unit) {
    val context = LocalContext.current
    val image = ConvertToImage.getResourceIdByName(context,meal.image)
    val proteinMatcher = GetNumberFromString.pattern.matcher(meal.protein)
    val caloriesMatcher = GetNumberFromString.pattern.matcher(meal.calories)
    val protein = if (proteinMatcher.find()) proteinMatcher.group() else ""
    val calories = if (caloriesMatcher.find()) caloriesMatcher.group() else ""
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.Small4)
            .wrapContentHeight()
            .clickable {
                onMealClick()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.Small2)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.Extreme4),
                contentScale = ContentScale.FillBounds
            )
            Surface(
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.6f),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {
                Column(
                    modifier = Modifier.padding(Dimens.Medium)
                ) {
                    Text(
                        text = if (Util.getCurrentLanguage() == Language.ENGLISH.code) meal.englishName else meal.arabicName,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(Dimens.Small2))
                    Row {
                        Text(
                            text = stringResource(id = R.string.mealScreen_protein) + protein + stringResource(id = R.string.mealScreen_p),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.width(Dimens.Small4))
                        Text(
                            text = stringResource(id = R.string.mealScreen_calories) + calories + stringResource(id = R.string.mealScreen_cal),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
}
