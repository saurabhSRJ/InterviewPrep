package com.example.interviewprepsample.data.di

import android.content.Context
import androidx.room.Room
import com.example.interviewprepsample.data.local.JokeDao
import com.example.interviewprepsample.data.local.JokeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideJokeDatabase(
        @ApplicationContext context: Context
    ): JokeDatabase {
        return Room
            .databaseBuilder(
                context.applicationContext,
                JokeDatabase::class.java,
                "joke_database"
            )
            .build()
    }

    @Provides
    fun provideTaskDao(database: JokeDatabase): JokeDao = database.getJokeData()
}