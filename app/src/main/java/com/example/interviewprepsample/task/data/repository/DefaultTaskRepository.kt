package com.example.interviewprepsample.task.data.repository

import com.example.core_data.task.TaskApiService
import com.example.core_database.TaskDao
import com.example.core_database.TaskStatus
import com.example.interviewprepsample.di.ApplicationScope
import com.example.interviewprepsample.di.IoDispatcher
import com.example.interviewprepsample.task.data.mapper.ObjectMapper.toNetworkTask
import com.example.interviewprepsample.task.data.mapper.ObjectMapper.toTask
import com.example.interviewprepsample.task.data.mapper.ObjectMapper.toTaskEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class DefaultTaskRepository @Inject constructor(
    private val localDataSource: TaskDao,
    private val networkDataSource: TaskApiService,
    @ApplicationScope private val scope: CoroutineScope,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : TaskRepository {
    override fun getTasksStream(): Flow<List<Task>> {
        return localDataSource.observeAll().map { tasks ->
            tasks.map {
                it.toTask()
            }
        }
    }

    override suspend fun getTasks(forceUpdate: Boolean): List<Task> {
        return withContext(dispatcher) {
            localDataSource.getAll().map { it.toTask() }
        }
    }

    override suspend fun refresh() {
        withContext(dispatcher) {
            val remoteTasks = networkDataSource.loadTasks()
            localDataSource.deleteAll()
            localDataSource.upsertAll(remoteTasks.map { it.toTaskEntity() })
        }
    }

    override fun getTaskStream(taskId: String): Flow<Task?> {
        return localDataSource.observeById(taskId).map { it.toTask() }
    }

    override suspend fun getTask(taskId: String, forceUpdate: Boolean): Task? {
        return localDataSource.getById(taskId)?.toTask()
    }

    override suspend fun refreshTask(taskId: String) {
        refresh()
    }

    override suspend fun createTask(title: String, description: String): String {
        val taskId = withContext(dispatcher) {
            UUID.randomUUID().toString()
        }
        val task = Task(
            title = title,
            description = description,
            id = taskId,
        )
        localDataSource.upsert(task.toTaskEntity())
        saveTasksToNetwork()
        return taskId
    }

    override suspend fun updateTask(taskId: String, title: String, description: String) {
        val task = localDataSource.getById(taskId)?.copy(
            title = title,
            description = description
        ) ?: throw Exception("task not found with id: $taskId")
        localDataSource.upsert(task)
    }

    override suspend fun completeTask(taskId: String) {
        localDataSource.updateTaskStatus(taskId, TaskStatus.COMPLETED)
        saveTasksToNetwork()
    }

    override suspend fun activateTask(taskId: String) {
        localDataSource.updateTaskStatus(taskId, TaskStatus.ACTIVE)
        saveTasksToNetwork()    }

    override suspend fun clearCompletedTasks() {
        localDataSource.deleteCompleted()
        saveTasksToNetwork()
    }

    override suspend fun deleteAllTasks() {
        localDataSource.deleteAll()
        saveTasksToNetwork()
    }

    override suspend fun deleteTask(taskId: String) {
        localDataSource.deleteById(taskId)
        saveTasksToNetwork()
    }

    private fun saveTasksToNetwork() {
        scope.launch {
            try {
                val localTasks = localDataSource.getAll()
                val networkTasks = withContext(dispatcher) {
                    localTasks.map { it.toNetworkTask() }
                }
                networkDataSource.saveTasks(networkTasks)
            } catch (e: Exception) {
                // In a real app you'd handle the exception e.g. by exposing a `networkStatus` flow
                // to an app level UI state holder which could then display a Toast message.
            }
        }
    }
}