package com.example.interviewprepsample.task.data.repository

import com.example.core_database.TaskStatus

data class Task(
    val id: String,
    val title: String,
    val description: String = "",
    val status: TaskStatus = TaskStatus.ACTIVE
)