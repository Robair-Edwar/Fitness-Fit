package com.example.fitnessfit.presentation.screen.dashboard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes

@Preview
@Composable
fun DaysPreview() {
    Days("","")
}

@Composable
fun Days(
    finished: String,
    remaining: String
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
                text = stringResource(id = R.string.dashboard_journey),
                fontWeight = FontWeight.Bold,
                fontSize = Sizes.Medium3,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start,
                maxLines = 1
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Small4),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DaysItem(title = stringResource(id = R.string.dashboard_finished), body = finished)
                DaysItem(title = stringResource(id = R.string.dashboard_remain), body = remaining)
            }
        }
    }
}
