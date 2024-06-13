package com.example.fitnessfit.presentation.screen.meals.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.fitnessfit.presentation.common.constants.Sizes

@Preview
@Composable
fun MovingRainbowTextPreview(){
    MovingRainbowText("More Than 110 Meal")
}



@Composable
fun MovingRainbowText(
    text: String,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val offset by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val colors = listOf(
        Color.Red, Color.Yellow, Color.Green, Color.Cyan, Color.Blue, Color.Magenta
    )
    val brush = Brush.horizontalGradient(colors)

    BasicText(
        text = text,
        style = TextStyle(
            fontSize = Sizes.Medium4,
            fontWeight = FontWeight.Bold,
            brush = brush
        ),
        modifier = modifier
            .fillMaxWidth()
            .offset(x = offset * 300.dp)
            .drawWithContent {
                drawContent()
            }
    )
}
