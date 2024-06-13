package com.example.fitnessfit.presentation.screen.settings.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import java.util.Locale

object ChangeLanguage {
    fun changeLanguage(context: Context, language: String){
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLayoutDirection(locale)
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}