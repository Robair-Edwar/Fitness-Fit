package com.example.fitnessfit.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * FROM meal WHERE englishCategory = :category")
    fun getMealByEnglishCategory(category: String): Flow<List<Meal>>

    @Query("SELECT * FROM meal WHERE arabicCategory = :category")
    fun getMealByArabicCategory(category: String): Flow<List<Meal>>

    @Query("SELECT * FROM meal WHERE id = :id")
    suspend fun getMealById(id: Int): Meal

    @Insert
    suspend fun insertMeal(meal: Meal)

    @Query("SELECT DISTINCT englishCategory FROM meal")
    fun getAllEnglishCategories(): Flow<List<String>>

    @Query("SELECT DISTINCT arabicCategory FROM meal")
    fun getAllArabicCategories(): Flow<List<String>>

}