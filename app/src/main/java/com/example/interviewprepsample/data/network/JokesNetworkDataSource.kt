package com.example.interviewprepsample.data.network

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class JokesNetworkDataSource @Inject constructor(
    private val jokesApiService: JokeApiService
): JokeApiService {
    private val accessMutex = Mutex()

    override suspend fun getJoke(): JokeResponse = accessMutex.withLock {
        jokesApiService.getJoke()
    }
}