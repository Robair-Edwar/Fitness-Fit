package com.example.fitnessfit.presentation.screen.splash

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessfit.R
import kotlinx.coroutines.delay


@Composable
fun AnimatedSplashScreen(onSplashEnd: () -> Unit) {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )
    LaunchedEffect (true){
        startAnimation = true
        delay(500L)
        onSplashEnd()
    }
    Splash(alpha = alphaAnim.value)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SplashPreviewLight() {
    Splash(1f)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SplashPreviewDark() {
    Splash(1f)
}

@Composable
fun Splash(alpha: Float) {
    Box(modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Icon(
            modifier = Modifier
                .size(200.dp)
                .alpha(alpha = alpha),
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = "Logo",
            tint = Color.Unspecified
        )
    }
}