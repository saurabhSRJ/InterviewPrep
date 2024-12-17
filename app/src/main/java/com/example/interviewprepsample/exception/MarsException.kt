package com.example.interviewprepsample.exception

import okio.IOException

sealed class MarsException: Exception() {
    data class NoInternetException(override val message: String = "No Internet"): MarsException()
    data class GenericException(override val message: String = "Something went wrong"): MarsException()
}

fun Throwable.getMarsException(): MarsException = when (this) {
    is IOException -> throw MarsException.NoInternetException()
    else -> throw MarsException.GenericException()
}