package com.example.core_database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    @TypeConverters(TaskStatusConverter::class)
    val status: TaskStatus
)

class TaskStatusConverter {
    @TypeConverter
    fun fromTaskStatus(status: TaskStatus): String {
        return status.name
    }

    @TypeConverter
    fun toTaskStatus(status: String): TaskStatus {
        return TaskStatus.valueOf(status)
    }
}