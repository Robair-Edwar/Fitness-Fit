package com.example.fitnessfit.di


import android.app.Application
import android.content.Context
import com.example.fitnessfit.data.local.datastore.SettingsRepositoryImpl
import com.example.fitnessfit.data.remote.repository.AuthenticationRepositoryImpl
import com.example.fitnessfit.data.remote.repository.FireStoreRepositoryImpl
import com.example.fitnessfit.domain.repository.AuthenticationRepository
import com.example.fitnessfit.domain.repository.FireStoreRepository
import com.example.fitnessfit.domain.repository.SettingsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth{
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun provideFirebaseFireStore(): FirebaseFirestore{
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideAuthenticationRepository(firebaseAuth: FirebaseAuth): AuthenticationRepository{
        return AuthenticationRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideFireStoreRepository(firebaseFireStore: FirebaseFirestore,firebaseAuth: FirebaseAuth): FireStoreRepository{
        return FireStoreRepositoryImpl(firebaseFireStore,firebaseAuth)
    }

    @Provides
    @ApplicationContext
    fun provideAppContext(application: Application): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(@ApplicationContext context: Application): SettingsRepository {
        return SettingsRepositoryImpl(context = context)
    }

}