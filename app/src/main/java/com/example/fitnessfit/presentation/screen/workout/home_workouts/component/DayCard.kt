package com.example.fitnessfit.presentation.screen.workout.home_workouts.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes

@Preview
@Composable
fun DayCardPreview() {
    DayCard("1",1,Color.Cyan){}
}

@Composable
fun DayCard(text: String,state: Int,color: Color, onClick: () -> Unit) {
    val baseModifier = Modifier
        .height(Dimens.Extreme3)
        .width(Dimens.Extreme1)
        .padding(Dimens.Small3)
        .border(
            width = Dimens.Small,
            color = MaterialTheme.colorScheme.onSurface,
            shape = RoundedCornerShape(10)
        )
    val clickableModifier = if (state != 2) {
        baseModifier.clickable(onClick = onClick)
    } else {
        baseModifier
    }
    Card(
        modifier = clickableModifier,
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
                text = text,
                color = if (state == 3) color else Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = Sizes.Medium4,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(Dimens.Small4))
            Icon(
                imageVector = if (state == 1) Icons.Default.Done else if (state == 2) Icons.Default.Lock else Icons.Default.PlayArrow,
                contentDescription = "Done",
                tint = if (state == 3) color else Color.Gray
            )
        }
    }
}
