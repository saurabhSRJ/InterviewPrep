package com.example.core_data.task

interface TaskApiService {

    suspend fun loadTasks(): List<NetworkTask>

    suspend fun saveTasks(newTasks: List<NetworkTask>)
}