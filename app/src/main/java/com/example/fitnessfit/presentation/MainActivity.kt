package com.example.fitnessfit.presentation

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnessfit.presentation.navigation.SetupNavGraph
import com.example.fitnessfit.presentation.screen.settings.SettingsViewModel
import com.example.fitnessfit.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.fitnessfit.presentation.screen.settings.util.ChangeLanguage
import com.example.fitnessfit.presentation.screen.settings.util.Language
import com.example.fitnessfit.presentation.screen.workout.workout.WorkoutScreen1
import com.example.fitnessfit.util.Util

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val settingsState by viewModel.settingsUiState.collectAsState()
    val context = LocalContext.current
    var layoutDirection by remember { mutableStateOf(LayoutDirection.Ltr) }

    LaunchedEffect(settingsState.language) {
        ChangeLanguage.changeLanguage(context, settingsState.language)
        layoutDirection = if (Util.getCurrentLanguage() == Language.ENGLISH.code) LayoutDirection.Ltr else LayoutDirection.Rtl
    }

    CompositionLocalProvider(
        LocalLayoutDirection provides layoutDirection
    ) {
        AppTheme(currentTheme = settingsState.theme) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                SetupNavGraph(settingsViewModel = viewModel)
            }
        }

    }
}