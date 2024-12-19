package com.example.interviewprepsample.data.network

import retrofit2.http.GET

interface JokeApiService {
    @GET("joke/Any")
    suspend fun getJoke(): JokeResponse
}