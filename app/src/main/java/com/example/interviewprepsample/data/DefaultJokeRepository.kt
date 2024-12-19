package com.example.interviewprepsample.data

import com.example.interviewprepsample.data.di.IoDispatcher
import com.example.interviewprepsample.data.local.JokeDao
import com.example.interviewprepsample.data.mapper.toJokeData
import com.example.interviewprepsample.data.mapper.toJokeEntity
import com.example.interviewprepsample.data.model.JokeData
import com.example.interviewprepsample.data.network.JokesNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultJokeRepository @Inject constructor(
    private val remoteDataSource: JokesNetworkDataSource,
    private val localDataSource: JokeDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : JokesRepository {
    override suspend fun getJoke(): JokeData {
        return withContext(dispatcher) {
            val response = remoteDataSource.getJoke()
            localDataSource.saveJoke(response.toJokeEntity())
            return@withContext response.toJokeData()
        }
    }

    override suspend fun markJokeAsFavourite(id: Long) {
        localDataSource.markJokeAsFavourite(id)
    }

    override suspend fun getNextJoke(): JokeData {
        val existingJokeIds = localDataSource.getAllJokes().map { it.id }
        val nextJoke = remoteDataSource.getJoke()
        if (existingJokeIds.contains(nextJoke.id)) {
            return remoteDataSource.getJoke().toJokeData()
        }
        return nextJoke.toJokeData()
    }

    override suspend fun getAllJokes(): Flow<List<JokeData>> {
        return localDataSource.observeAll().map { jokes ->
            jokes.map { it.toJokeData() }
        }
    }

    override fun getFavouriteJokes(): Flow<List<JokeData>> {
        return localDataSource.getFavouriteJokes().map { jokes ->
            jokes.map { it.toJokeData() }
        }
    }

    override suspend fun deleteJokeById(id: Long) {
        localDataSource.deleteJokeById(id)
    }
}