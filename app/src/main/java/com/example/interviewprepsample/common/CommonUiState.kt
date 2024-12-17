package com.example.interviewprepsample.common

data class CommonUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false
)

val successUiState = CommonUiState(isSuccess = true)
val loadingUiState = CommonUiState(isLoading = true)
val errorUiState = CommonUiState(isError = true)