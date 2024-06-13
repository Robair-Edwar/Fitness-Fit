package com.example.fitnessfit.domain.repository

import com.example.fitnessfit.data.local.room.Meal
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    fun getAllEnglishCategories(): Flow<List<String>>

    fun getAllArabicCategories(): Flow<List<String>>

    fun getMealByEnglishCategory(category: String): Flow<List<Meal>>

    fun getMealByArabicCategory(category: String): Flow<List<Meal>>

    suspend fun getMealById(id: Int): Meal

    suspend fun insertMeal(meal: Meal)
}