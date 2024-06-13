package com.example.fitnessfit.presentation.screen.meals.meals_categories_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.presentation.common.constants.Dimens

@Preview
@Composable
fun MealsCategoryItemPreview() {
    MealsCategoryItem("") {}
}

@Composable
fun MealsCategoryItem(category: String, onCategoryClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.Extreme2)
            .border(width = Dimens.Small, color = MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(10)),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.Small3),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onCategoryClick()
                }
                .padding(Dimens.Small2)
            ,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(vertical = Dimens.Small3),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }

}