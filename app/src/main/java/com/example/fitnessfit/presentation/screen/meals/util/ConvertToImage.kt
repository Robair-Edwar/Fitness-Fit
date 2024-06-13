package com.example.fitnessfit.presentation.screen.meals.util

import android.annotation.SuppressLint
import android.content.Context

object ConvertToImage {
    @SuppressLint("DiscouragedApi")
    fun getResourceIdByName(context: Context, resourceName: String): Int {
        return context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    }
}