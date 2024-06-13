package com.example.fitnessfit.presentation.screen.meals.meal_screen.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.data.local.room.Meal
import com.example.fitnessfit.presentation.screen.meals.meals_screen.component.meal
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import com.example.fitnessfit.R
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.screen.meals.util.ConvertToImage
import com.example.fitnessfit.presentation.screen.meals.util.GetNumberFromString
import com.example.fitnessfit.util.Language
import com.example.fitnessfit.util.Util

@Preview
@Composable
fun MealItemPreview() {
    MealItem(meal)
}
@Composable
fun MealItem(meal: Meal, modifier: Modifier = Modifier,) {

    val isEnglish = Util.getCurrentLanguage() == Language.ENGLISH.code

    val name = if (isEnglish) meal.englishName else meal.arabicName
    val category = if (isEnglish) meal.englishCategory else meal.arabicCategory
    val description = if (isEnglish) meal.englishDescription else meal.arabicDescription
    val ingredients = if (isEnglish) meal.englishIngredients else meal.arabicIngredients
    val instructions = if (isEnglish) meal.englishInstructions else meal.arabicInstructions

    val proteinMatcher = GetNumberFromString.pattern.matcher(meal.protein)
    val caloriesMatcher = GetNumberFromString.pattern.matcher(meal.calories)

    val protein = if (proteinMatcher.find()) proteinMatcher.group() else ""
    val calories = if (caloriesMatcher.find()) caloriesMatcher.group() else ""

    val context = LocalContext.current
    val image = ConvertToImage.getResourceIdByName(context, meal.image)

    Card(
        elevation = CardDefaults.cardElevation(Dimens.Small2),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(Dimens.Medium2)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.Medium)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(Dimens.Medium))

            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.Extreme4)
                    .clip(shape = RoundedCornerShape(10)),
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.height(Dimens.Medium))

            Text(
                text = category,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(Dimens.Small3))

            Text(
                text = description,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(Dimens.Medium))

            Text(
                text = stringResource(id = R.string.mealScreen_protein) + protein + stringResource(id = R.string.mealScreen_p),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = stringResource(id = R.string.mealScreen_calories) + calories + stringResource(id = R.string.mealScreen_cal),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(Dimens.Medium))

            Text(
                text = stringResource(id = R.string.mealScreen_ingredients),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary
            )

            Text(
                text = ingredients,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(Dimens.Medium))

            Text(
                text = stringResource(id = R.string.mealScreen_instructions),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary
            )

            Text(
                text = instructions,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}