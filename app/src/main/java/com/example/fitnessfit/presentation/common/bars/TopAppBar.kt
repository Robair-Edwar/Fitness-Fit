package com.example.fitnessfit.presentation.common.bars

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessfit.presentation.common.constants.Sizes
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar("",TextAlign.Start)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(text: String, alignment: TextAlign = TextAlign.Start, color: Color = MaterialTheme.colorScheme.primary) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color
        ),
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = Sizes.Medium3),
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = alignment,
                fontWeight = FontWeight.Bold
            )
        }
    )
}