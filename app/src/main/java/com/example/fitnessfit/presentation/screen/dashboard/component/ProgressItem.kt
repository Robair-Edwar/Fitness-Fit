package com.example.fitnessfit.presentation.screen.dashboard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes

@Preview
@Composable
fun ProgressItemPreview() {
    ProgressItem("Week 1",5, Color.Blue)
}

@Composable
fun ProgressItem(
    name: String,
    progress: Int,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Small4)
            .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(20))
    ) {
        Column(
            modifier = Modifier.padding(Dimens.Small4)
        ) {
            Text(
                text = name + String.format("%.2f", progress.toFloat() / 8 * 100) + "%",
                fontSize = Sizes.Medium2,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(Dimens.Small3))
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.Small3)
                    .clip(shape = RoundedCornerShape(50)),
                progress = { progress.toFloat()/8f },
                color = color,
                trackColor = Color.Gray
            )
        }
    }
}
