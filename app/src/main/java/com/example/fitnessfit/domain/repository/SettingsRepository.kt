package com.example.fitnessfit.domain.repository

import kotlinx.coroutines.flow.Flow


interface SettingsRepository {
    suspend fun setLanguage(language: String)
    suspend fun getLanguage(): Flow<String>
    suspend fun setTheme(theme: Int)
    suspend fun getTheme(): Flow<Int>
}