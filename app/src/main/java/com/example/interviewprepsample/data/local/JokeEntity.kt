package com.example.interviewprepsample.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "joke_table")
data class JokeEntity(
    @PrimaryKey
    val id: Long,
    val joke: String?,
    val isFavourite: Boolean
)