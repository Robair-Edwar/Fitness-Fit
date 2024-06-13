package com.example.fitnessfit.presentation.common

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.FileProvider
import com.example.fitnessfit.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

@Preview
@Composable
fun SharePreview() {
    Share("") {
        Text(text = "Hello")
    }
}

@Composable
fun Share(text: String,content: @Composable () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var view: ComposeView? = null

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        AndroidView(factory = { context ->
            view = ComposeView(context).apply {
                setContent {
                    content()
                }
            }
            view!!
        })
        Spacer(modifier = Modifier.height(14.dp))
        Button(
            modifier = Modifier
                .wrapContentSize(),
            shape = RoundedCornerShape(30),
            onClick = {
                coroutineScope.launch {
                    val bitmap = view?.toBitmap()
                    if (bitmap != null) {
                        shareBitmap(text,bitmap, context)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
        ) {
            Text(
                text = text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

fun View.toBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    this.draw(canvas)
    return bitmap
}

@SuppressLint("SetWorldReadable")
suspend fun shareBitmap(text: String,bitmap: Bitmap, context: Context) {
    val file = withContext(Dispatchers.IO) {
        val file = File(
            context.externalCacheDir,
            "image.png"
        )
        val fOut = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut)
        fOut.flush()
        fOut.close()
        file.setReadable(true, false)
        file
    }
    val fileUri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    val intent = Intent(Intent.ACTION_SEND).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
        putExtra(Intent.EXTRA_STREAM, fileUri)
        type = "image/png"
    }
    context.startActivity(Intent.createChooser(intent,text))
}