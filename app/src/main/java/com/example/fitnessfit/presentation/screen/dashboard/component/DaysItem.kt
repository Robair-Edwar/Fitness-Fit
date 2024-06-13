package com.example.fitnessfit.presentation.screen.dashboard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes

@Preview
@Composable
fun DaysItemPreview() {
    DaysItem("","")
}

@Composable
fun DaysItem(
    title: String,
    body: String
) {
    Card {
        Column(
            modifier = Modifier
                .width(Dimens.Extreme3),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(Dimens.Small4))
            Text(
                text = body,
                fontWeight = FontWeight.Bold,
                fontSize = Sizes.Medium2,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(Dimens.Small2))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = Sizes.Medium2,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.height(Dimens.Small4))
    }
}
