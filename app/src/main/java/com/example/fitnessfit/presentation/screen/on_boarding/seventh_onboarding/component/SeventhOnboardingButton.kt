package com.example.fitnessfit.presentation.screen.on_boarding.seventh_onboarding.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes

@Preview
@Composable
fun SeventhOnboardingButtonPreview() {
    SeventhOnboardingButton(R.string.onboarding7_motivate_4,true){}
}

@Composable
fun SeventhOnboardingButton(
    text: Int,
    buttonState: Boolean,
    onChoiceSelected: () -> Unit
) {
    Button(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(Dimens.Small3)
            .clip(RoundedCornerShape(50))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(50)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (buttonState) {
                MaterialTheme.colorScheme.tertiary
            } else {
                MaterialTheme.colorScheme.background
            }
        ),
        onClick = {
            onChoiceSelected()
        }) {
        Text(
            modifier = Modifier
                .height(Dimens.Large)
                .fillMaxWidth(),
            text = stringResource(id = text),
            fontSize = Sizes.Medium2,
            color = if (buttonState) {
                MaterialTheme.colorScheme.background
            } else {
                MaterialTheme.colorScheme.onBackground
            },
            textAlign = TextAlign.Center,
            lineHeight = Sizes.Medium3
        )
    }
}