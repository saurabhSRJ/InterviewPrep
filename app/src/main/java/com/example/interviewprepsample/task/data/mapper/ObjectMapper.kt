package com.example.interviewprepsample.task.data.mapper

import com.example.core_data.task.NetworkTask
import com.example.core_database.TaskEntity
import com.example.interviewprepsample.task.data.repository.Task

object ObjectMapper {
    fun TaskEntity.toTask() = Task(
        id = id,
        title = title,
        description = description,
        status = status
    )

    fun TaskEntity.toNetworkTask() = NetworkTask(
        id = id,
        title = title,
        description = description,
        status = status
    )

    fun Task.toTaskEntity() = TaskEntity(
        id = id,
        title = title,
        description = description,
        status = status
    )

    fun NetworkTask.toTaskEntity() = TaskEntity(
        id = id,
        title = title,
        description = description,
        status = status
    )
}