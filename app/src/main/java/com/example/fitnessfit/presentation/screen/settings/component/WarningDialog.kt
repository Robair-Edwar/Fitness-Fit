package com.example.fitnessfit.presentation.screen.settings.component

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens

@Preview
@Composable
fun WarningDialogPreview() {
    WarningDialog("are you sure",{},{})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarningDialog(
    text: String,
    onDismissClick: () -> Unit,
    onYesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BasicAlertDialog(
        onDismissRequest = { onDismissClick() },
        modifier = modifier
            .clip(RoundedCornerShape(20))
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(20))
            .padding(Dimens.Medium),
        content = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.settingsScreen_warning),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = Dimens.Small3)
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = Dimens.Medium)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismissClick) {
                        Text(stringResource(id = R.string.settingsScreen_no))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = onYesClick) {
                        Text(stringResource(id = R.string.settingsScreen_yes))
                    }
                }
            }
        }
    )
}