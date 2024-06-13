package com.example.fitnessfit.presentation.screen.settings.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes

@Preview
@Composable
fun IsEmailVerifiedPreview() {
    IsEmailVerified(true)
}

@Composable
fun IsEmailVerified(isEmailVerified: Boolean) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.Medium3)
            .height(Dimens.Large3)
            .clip(RoundedCornerShape(20))
            .background(color = MaterialTheme.colorScheme.onSecondaryContainer, shape = RoundedCornerShape(20))
            .border(
                width = Dimens.Small,
                color = if (isEmailVerified){
                        Color.Green
                    }else{
                        Color.Red
                    },
                shape = RoundedCornerShape(20)
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            modifier = Modifier.padding(horizontal = Dimens.Medium2),
            text = stringResource(id = R.string.settings_isVerified),
            textAlign = TextAlign.Start,
            fontSize = Sizes.Medium2,
            color = MaterialTheme.colorScheme.onSecondary,
            maxLines = 1
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .padding(end = Dimens.Large)
                .clip(CircleShape)
                .background(Color.White, CircleShape)
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = if (isEmailVerified){
                    Icons.Default.Done
                }else {
                    Icons.Default.Close
                },
                contentDescription = "Done",
                tint = if (isEmailVerified){
                    Color.Green
                }else {
                    Color.Red
                }
            )
        }
    }
}