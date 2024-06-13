package com.example.fitnessfit.presentation.screen.settings.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes

@Preview
@Composable
fun ActionButtonPreview() {
    ActionButton(
        R.string.settings_changePassword,
        icon = Icons.Default.Lock
    ){}
}

@Composable
fun ActionButton(
    @StringRes text: Int,
    icon: ImageVector,
    onClickEvent: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.Medium3)
            .height(Dimens.Large3)
            .clip(RoundedCornerShape(20))
            .background(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                shape = RoundedCornerShape(20)
            ),
        onClick = {
            onClickEvent()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Icon(
            modifier = Modifier.padding(end = Dimens.Small4),
            imageVector = icon,
            contentDescription = "Account",
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(text),
            textAlign = TextAlign.Start,
            fontSize = Sizes.Medium2,
            color = MaterialTheme.colorScheme.onSecondary,
            maxLines = 1
        )
    }
}