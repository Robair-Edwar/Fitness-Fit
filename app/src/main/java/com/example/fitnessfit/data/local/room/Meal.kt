package com.example.fitnessfit.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class Meal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String,
    val englishName: String,
    val arabicCategory: String,
    val englishCategory: String,
    val arabicDescription: String,
    val englishDescription: String,
    val arabicIngredients: String,
    val englishIngredients: String,
    val arabicInstructions: String,
    val englishInstructions: String,
    val protein: String,
    val calories: String,
    val image: String
)