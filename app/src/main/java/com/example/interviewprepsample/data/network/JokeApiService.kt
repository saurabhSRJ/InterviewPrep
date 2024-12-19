package com.example.interviewprepsample.data.network

import retrofit2.http.GET

interface JokeApiService {
    @GET("/Any")
    suspend fun getJoke(): JokeResponse
}