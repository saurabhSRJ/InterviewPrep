package com.example.core_data.task

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

private const val SERVICE_LATENCY_IN_MILLIS = 2000L

class TaskNetworkDataSource @Inject constructor(
    private val taskApiService: TaskApiService
) : TaskApiService {
    private val accessMutex = Mutex()
    private var tasks = listOf(
        NetworkTask(
            id = "PISA",
            title = "Build tower in Pisa",
            description = "Ground looks good, no foundation work required."
        ),
        NetworkTask(
            id = "TACOMA",
            title = "Finish bridge in Tacoma",
            description = "Found awesome girders at half the cost!"
        )
    )

    override suspend fun loadTasks(): List<NetworkTask> = accessMutex.withLock {
        delay(SERVICE_LATENCY_IN_MILLIS)
        return tasks
    }

    override suspend fun saveTasks(newTasks: List<NetworkTask>) = accessMutex.withLock{
        delay(SERVICE_LATENCY_IN_MILLIS)
        tasks = newTasks
    }
}