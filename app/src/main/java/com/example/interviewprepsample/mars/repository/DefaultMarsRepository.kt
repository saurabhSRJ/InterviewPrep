package com.example.interviewprepsample.mars.repository

import com.example.core_data.mars.MarsNetworkDataSource
import com.example.core_data.mars.MarsPhoto
import com.example.interviewprepsample.di.IoDispatcher
import com.example.interviewprepsample.exception.getMarsException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class DefaultMarsRepository @Inject constructor(
    private val marsNetworkDataSource: MarsNetworkDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : MarsRepository {
    override fun getPhotos() =
        flow {
            emit(marsNetworkDataSource.getPhotos())
        }.flowOn(dispatcher)

    override suspend fun getPhotosNormal(): List<MarsPhoto> = withContext(dispatcher) {
        return@withContext try {
            marsNetworkDataSource.getPhotos()
        } catch (cancellationException: CancellationException) {
            throw cancellationException
        }
        catch (e: Exception) {
            throw e.getMarsException()
        }
    }
}