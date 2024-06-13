package com.example.fitnessfit.presentation.screen.dashboard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun ProgressPreview() {
    Progress(7,2,0,0)
}

@Composable
fun Progress(
    week1: Int,
    week2: Int,
    week3: Int,
    week4: Int
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
        )
    ) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Small4),
                text = stringResource(id = R.string.dashboard_progress),
                fontWeight = FontWeight.Bold,
                fontSize = Sizes.Medium3,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start,
                maxLines = 1
            )
            ProgressItem(
                name = stringResource(id = R.string.dashboard_firstWeekProgress),
                progress = week1,
                color = Color.Cyan
            )
            ProgressItem(
                name = stringResource(id = R.string.dashboard_secondWeekProgress),
                progress = week2,
                color = Color.Green
            )
            ProgressItem(
                name = stringResource(id = R.string.dashboard_thirdWeekProgress),
                progress = week3,
                color = Color.Yellow
            )
            ProgressItem(
                name = stringResource(id = R.string.dashboard_fourthWeekProgress),
                progress = week4,
                color = Color.Red
            )
        }
    }
}
