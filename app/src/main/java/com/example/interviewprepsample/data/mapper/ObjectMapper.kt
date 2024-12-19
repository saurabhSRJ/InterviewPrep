package com.example.interviewprepsample.data.mapper

import com.example.interviewprepsample.data.local.JokeEntity
import com.example.interviewprepsample.data.model.JokeData
import com.example.interviewprepsample.data.network.JokeResponse

fun JokeResponse.toJokeData() = JokeData(
    joke = getCombinedJoke(),
    id = id,
    error = error,
    isFavourite = false
)

fun JokeEntity.toJokeData() = JokeData(
    joke = joke,
    id = id,
    error = false,
    isFavourite = isFavourite
)

fun JokeResponse.toJokeEntity() = JokeEntity(
    joke = getCombinedJoke(),
    id = id,
    isFavourite = false
)