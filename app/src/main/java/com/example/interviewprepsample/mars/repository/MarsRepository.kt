package com.example.interviewprepsample.mars.repository

import com.example.core_data.mars.MarsPhoto
import kotlinx.coroutines.flow.Flow

interface MarsRepository {
    fun getPhotos(): Flow<List<MarsPhoto>>

    suspend fun getPhotosNormal(): List<MarsPhoto>
}