package com.example.fitnessfit.presentation.screen.dashboard.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun UserInfoItemPreview() {
    UserInfoItem(-1,"")
}

@Composable
fun UserInfoItem(
    @StringRes title: Int,
    body: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.Medium2, vertical = Dimens.Small3)
            .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(20)),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(top = Dimens.Small4, bottom = Dimens.Small4, start = Dimens.Small4, end = Dimens.Small2),
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            fontSize = Sizes.Medium2,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Start,
            maxLines = 1
        )
        Text(
            modifier = Modifier.padding(),
            text = body,
            fontWeight = FontWeight.Bold,
            fontSize = Sizes.Medium2,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Start,
            maxLines = 1
        )
    }
}
