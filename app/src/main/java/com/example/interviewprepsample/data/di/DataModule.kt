package com.example.interviewprepsample.data.di

import com.example.interviewprepsample.data.DefaultJokeRepository
import com.example.interviewprepsample.data.JokesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindJokeRepository(repo: DefaultJokeRepository): JokesRepository
}