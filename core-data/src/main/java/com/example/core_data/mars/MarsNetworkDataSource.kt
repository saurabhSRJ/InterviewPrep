package com.example.core_data.mars

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class MarsNetworkDataSource @Inject constructor(
    private val marsApiService: MarsApiService
) : MarsApiService {
    private val accessMutex = Mutex()
    override suspend fun getPhotos(): List<MarsPhoto> = accessMutex.withLock {
        return marsApiService.getPhotos()
    }
}