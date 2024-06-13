package com.example.fitnessfit.presentation.screen.workout.home_workouts.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes

@Preview
@Composable
fun RestCardPreview() {
    RestCard("Rest",1,Color.Green,{})
}

@Composable
fun RestCard(
    text: String,state: Int,color: Color, onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(Dimens.Extreme3)
            .width(Dimens.Extreme1)
            .padding(Dimens.Small3)
            .border(
                width = Dimens.Small,
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(10)
            ),
        elevation = CardDefaults.cardElevation(
            focusedElevation = Dimens.Small4
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (state == 1) color else MaterialTheme.colorScheme.surfaceContainerHighest
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = Dimens.Small4),
                text = text,
                color = if (state == 3) color else Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = Sizes.Medium2,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(Dimens.Small3))
            Checkbox(
                checked = if (state == 1) true else false,
                onCheckedChange = { if (state == 3) onClick()  }
            )
        }
    }
}
