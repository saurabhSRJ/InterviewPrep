package com.example.core_data.task

import com.example.core_database.TaskStatus

data class NetworkTask(
    val id: String,
    val title: String,
    val description: String,
    val priority: Int? = null,
    val status: TaskStatus = TaskStatus.ACTIVE
)