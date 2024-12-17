package com.example.interviewprepsample.di

import com.example.interviewprepsample.mars.repository.DefaultMarsRepository
import com.example.interviewprepsample.mars.repository.MarsRepository
import com.example.interviewprepsample.task.data.repository.DefaultTaskRepository
import com.example.interviewprepsample.task.data.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindTaskRepository(taskRepository: DefaultTaskRepository): TaskRepository

    @Binds
    abstract fun bindMarsRepository(marsRepository: DefaultMarsRepository): MarsRepository
}