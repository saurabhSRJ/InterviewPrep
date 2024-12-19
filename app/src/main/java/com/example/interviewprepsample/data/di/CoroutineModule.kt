package com.example.interviewprepsample.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Qualifier
import kotlinx.coroutines.Dispatchers

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {
    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher() = Dispatchers.Default

    @Provides
    @IoDispatcher
    fun provideIoDispatcher() = Dispatchers.IO

}