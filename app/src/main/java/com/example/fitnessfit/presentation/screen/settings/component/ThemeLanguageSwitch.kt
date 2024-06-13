package com.example.fitnessfit.presentation.screen.settings.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun ThemeLanguageSwitchPreview() {
    ThemeLanguageSwitch(
        R.string.settings_arabicLanguage,
        true
    ){}
}

@Composable
fun ThemeLanguageSwitch(
    @StringRes text: Int,
    isChecked: Boolean,
    onSwitchClick: (isChecked: Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.Medium3)
            .height(Dimens.Large3)
            .clip(RoundedCornerShape(20))
            .background(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                shape = RoundedCornerShape(20)
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = Dimens.Medium2),
            text = stringResource(text),
            textAlign = TextAlign.Start,
            fontSize = Sizes.Medium2,
            color = MaterialTheme.colorScheme.onSecondary,
            maxLines = 1
        )
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            modifier = Modifier.padding(end = Dimens.Medium4),
            checked = isChecked,
            onCheckedChange = { isChecked ->
                onSwitchClick(isChecked)
              },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = MaterialTheme.colorScheme.onPrimary,
                checkedTrackColor = MaterialTheme.colorScheme.background,
                uncheckedTrackColor = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}