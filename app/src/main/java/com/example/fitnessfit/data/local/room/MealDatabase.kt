package com.example.fitnessfit.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Meal::class], version = 1, exportSchema = false)
abstract class MealDatabase: RoomDatabase() {
    abstract fun mealDao(): MealDao
}