package com.example.fitnessfit.presentation.screen.dashboard.component

import android.service.autofill.UserData
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun UserInfoPreview() {
}

@Composable
fun UserInfo(
    name: String,
    gender: String,
    age: String,
    height: String,
    weight: String
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
                text = stringResource(id = R.string.dashboard_info),
                fontWeight = FontWeight.Bold,
                fontSize = Sizes.Medium3,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start,
                maxLines = 1
            )
            UserInfoItem(title = R.string.dashboard_name, body = name)
            UserInfoItem(title = R.string.dashboard_gender, body = gender)
            UserInfoItem(title = R.string.dashboard_age, body = age)
            UserInfoItem(title = R.string.dashboard_height, body = height)
            UserInfoItem(title = R.string.dashboard_weight, body = weight)
        }
    }
}
