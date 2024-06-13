package com.example.fitnessfit.di

import android.content.Context
import androidx.room.Room
import com.example.fitnessfit.data.local.room.MealDao
import com.example.fitnessfit.data.local.room.MealDatabase
import com.example.fitnessfit.data.local.room.MealRepositoryImpl
import com.example.fitnessfit.domain.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "meal_database.db"

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MealDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = MealDatabase::class.java,
            name = "meal_database"
        )
            .createFromAsset(DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideMealDao(database: MealDatabase): MealDao{
        return database.mealDao()
    }

    @Provides
    @Singleton
    fun provideMealRepository(mealDao: MealDao): MealRepository {
        return MealRepositoryImpl(mealDao)
    }

}