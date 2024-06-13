package com.example.fitnessfit.presentation.screen.auth.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.common.constants.Sizes
import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Label


@Composable
fun AuthTextField(
    placeholder: String,
    modifier: Modifier = Modifier,
    text: String,
    type: String,
    errorState: Int,
    onTextChange: (text: String) -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
    var passwordVisible by remember {
        mutableStateOf(false)
    }
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20))
            .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(20))
            .border(
                width = Dimens.Small,
                color = if (errorState == -1) Color.Green else Color.Red,
                shape = RoundedCornerShape(20)
            ),
        value = text,
        onValueChange = { newText ->
            onTextChange(newText)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
        ),
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = Sizes.Medium2,
            textAlign = TextAlign.Start,
        ), placeholder = {
            Text(
                text = placeholder,
                fontSize = Sizes.Medium2,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Start,
                maxLines = 1
            )
        },
        maxLines = 1,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        visualTransformation = if (type == "password") if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
           val image = if (passwordVisible) painterResource(id = R.drawable.eye_on) else painterResource(id = R.drawable.eye_off)
           if (type == "password"){
               IconButton(
                   modifier = Modifier.padding(end = Dimens.Small4).size(Dimens.Medium4),
                   onClick = { passwordVisible = !passwordVisible }) {
                   Icon(painter = image, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
               }
           }
        },
        keyboardOptions = when (placeholder) {
            "Name" -> KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )

            "Email" -> KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )

            "Password" -> KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )

            else -> KeyboardOptions(
                keyboardType = 
                if (type == "email") KeyboardType.Email else if (type == "password") KeyboardType.Password else KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        }
    )
}