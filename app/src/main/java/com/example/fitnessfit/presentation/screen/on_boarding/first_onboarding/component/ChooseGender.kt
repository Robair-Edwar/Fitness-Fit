package com.example.fitnessfit.presentation.screen.on_boarding.first_onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes

@Preview
@Composable
fun ChooseGenderPreview() {
    ChooseGender(onGenderChoice = {}, gender = 1)
}

@Composable
fun ChooseGender(
    modifier: Modifier = Modifier,
    gender: Int,
    onGenderChoice: (gender: Int) -> Unit
    ) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 6f)
    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .height(400.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(30))
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.inverseSurface,
                        shape = RoundedCornerShape(30)
                    )
                    .background(
                        color = if (gender == 1) {
                            MaterialTheme.colorScheme.tertiary
                        } else {
                            MaterialTheme.colorScheme.background
                        }
                    )
                    .clickable {
                        onGenderChoice(1)
                    },
                painter = painterResource(id = R.drawable.male),
                contentDescription = "male",
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(Dimens.Medium))
            Text(
                modifier = Modifier
                    .clickable {
                        onGenderChoice(1)
                    },
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.onboarding1_male),
                fontSize = Sizes.Medium4,
                color = if (gender == 1 ) {
                    MaterialTheme.colorScheme.tertiary
                } else {
                    MaterialTheme.colorScheme.onBackground
                },
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .height(400.dp)
                    .width(150.dp)
                    .padding()
                    .clip(RoundedCornerShape(30))
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.inverseSurface,
                        shape = RoundedCornerShape(30)
                    )
                    .background(
                        color = if (gender == 2) {
                            MaterialTheme.colorScheme.tertiary
                        } else {
                            MaterialTheme.colorScheme.background
                        }
                    )
                    .clickable {
                        onGenderChoice(2)
                    },
                painter = painterResource(id = R.drawable.female),
                contentDescription = "female",
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(Dimens.Medium))
            Text(
                modifier = Modifier
                    .clickable {
                        onGenderChoice(2)
                    },
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.onboarding1_female),
                fontSize = Sizes.Medium4,
                color = if (gender == 2 ) {
                    MaterialTheme.colorScheme.tertiary
                } else {
                    MaterialTheme.colorScheme.onBackground
                },
                textAlign = TextAlign.Center
            )
        }
    }
}