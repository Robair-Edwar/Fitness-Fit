package com.example.fitnessfit.presentation.screen.workout.workout.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes
import com.example.fitnessfit.presentation.screen.workout.workout.util.PlayWhistleSound
import com.example.fitnessfit.presentation.screen.workout.workout.util.Vibrate
import com.example.fitnessfit.presentation.screen.workout.workout.util.Vibrate.vibrateDevice
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun SecondsCounterPreview() {
    SecondsCounter(10){}
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SecondsCounter(startingNumber: Int, onFinish: () -> Unit) {
    var seconds by remember { mutableStateOf(startingNumber) }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        PlayWhistleSound.playWhistleSound(context)
        vibrateDevice(context)
        while (seconds > 0) {
            delay(1000L)
            seconds--
        }
        if (seconds == 0) {
            PlayWhistleSound.playWhistleSound(context)
            vibrateDevice(context)
        }
        onFinish()
    }

    Box(
        modifier = Modifier
            .height(Dimens.Large4)
            .width(Dimens.Large3)
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            fontSize = Sizes.Medium4,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            text = seconds.toString(),
            textAlign = TextAlign.Center
        )
    }
}
