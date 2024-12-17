package com.example.core_database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    /**
     * Observes list of tasks.
     *
     * @return all tasks.
     */
    @Query("SELECT * FROM task")
    fun observeAll(): Flow<List<TaskEntity>>

    /**
     * Observes a single task.
     *
     * @param taskId the task id.
     * @return the task with taskId.
     */
    @Query("SELECT * FROM task WHERE id = :taskId")
    fun observeById(taskId: String): Flow<TaskEntity>

    /**
     * Insert or update an task
     */
    @Upsert
    suspend fun upsert(task: TaskEntity)

    @Upsert
    suspend fun upsertAll(task: List<TaskEntity>)

    @Query("SELECT * from task")
    suspend fun getAll(): List<TaskEntity>

    @Query("SELECT * from task WHERE id=:id")
    suspend fun getById(id: String): TaskEntity?

    @Query("DELETE FROM task WHERE status='COMPLETED'")
    suspend fun deleteCompleted(): Int

    @Query("DELETE from task")
    suspend fun deleteAll()

    @Query("DELETE from task where id=:id")
    suspend fun deleteById(id: String): Int

    @Query("UPDATE task SET status=:status WHERE id=:id")
    suspend fun updateTaskStatus(id: String, status: TaskStatus)
}