package com.example.interviewprepsample.data

import com.example.interviewprepsample.data.model.JokeData
import kotlinx.coroutines.flow.Flow

interface JokesRepository {
    suspend fun getJoke(): JokeData

    suspend fun markJokeAsFavourite(id: Long)

    suspend fun getNextJoke(): JokeData

    suspend fun getAllJokes() : Flow<List<JokeData>>

    fun getFavouriteJokes(): Flow<List<JokeData>>

    suspend fun deleteJokeById(id: Long)
}