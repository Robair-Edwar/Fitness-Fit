package com.example.fitnessfit.presentation.screen.workout.workout.component

import android.os.Build.VERSION.SDK_INT
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens

@Preview
@Composable
fun WorkoutGifPreview() {
    WorkoutGif(R.drawable.sit_ups,Modifier)
}

@Composable
fun WorkoutGif(
    @DrawableRes gif: Int,
    modifier: Modifier
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = gif).apply(block = {
                size(500)
            }).build(), imageLoader = imageLoader
        ),
        contentScale = ContentScale.FillHeight,
        contentDescription = null,
        modifier = modifier
            .fillMaxHeight(fraction = .5f)
            .border(width = Dimens.Small, color = MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(10))
            .clip(RoundedCornerShape(10))
            .fillMaxWidth(),
    )
}
