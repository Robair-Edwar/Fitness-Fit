package com.example.fitnessfit.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.fitnessfit.domain.repository.SettingsRepository
import com.example.fitnessfit.presentation.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Locale
import javax.inject.Inject


class SettingsRepositoryImpl @Inject constructor(
    private val context: Context
): SettingsRepository {

    companion object {
        private val THEME_KEY = (intPreferencesKey("theme"))
        private const val DEFAULT_THEME = 0
        private val LANGUAGE_KEY = (stringPreferencesKey("language"))
    }

    override suspend fun setLanguage(language: String) {
        context.dataStore.edit { settings ->
            settings[LANGUAGE_KEY] = language
        }
    }

    override suspend fun getLanguage(): Flow<String>{
        return context.dataStore.data.map { settings ->
            settings[LANGUAGE_KEY] ?: Locale.getDefault().language
        }
    }

    override suspend fun setTheme(theme: Int) {
        context.dataStore.edit { settings ->
            settings[THEME_KEY] = theme
        }
    }

    override suspend fun getTheme(): Flow<Int> {
        return context.dataStore.data.map { settings ->
            settings[THEME_KEY] ?: DEFAULT_THEME
        }
    }

}