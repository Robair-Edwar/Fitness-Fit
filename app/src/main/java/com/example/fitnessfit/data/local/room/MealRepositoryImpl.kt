package com.example.fitnessfit.data.local.room

import com.example.fitnessfit.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealDao: MealDao
): MealRepository {

    override fun getAllEnglishCategories(): Flow<List<String>> {
        return mealDao.getAllEnglishCategories()
    }

    override fun getAllArabicCategories(): Flow<List<String>> {
        return mealDao.getAllArabicCategories()
    }

    override fun getMealByEnglishCategory(category: String): Flow<List<Meal>> {
        return mealDao.getMealByEnglishCategory(category)
    }

    override fun getMealByArabicCategory(category: String): Flow<List<Meal>> {
        return mealDao.getMealByArabicCategory(category)
    }

    override suspend fun getMealById(id: Int): Meal {
        return mealDao.getMealById(id)
    }

    override suspend fun insertMeal(meal: Meal) {
        mealDao.insertMeal(meal)
    }


}