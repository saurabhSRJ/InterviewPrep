package com.example.interviewprepsample.data.model

data class JokeData(
    val joke: String?,
    val id: Long,
    val error: Boolean = false,
    val isFavourite: Boolean = false
)
