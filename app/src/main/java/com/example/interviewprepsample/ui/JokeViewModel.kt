package com.example.interviewprepsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewprepsample.data.JokesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class JokeUiState {
    object Loading : JokeUiState()
    data class Success(val joke: String, val id: Long) : JokeUiState()
    data class Error(val message: String) : JokeUiState()
}

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val jokesRepository: JokesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<JokeUiState>(JokeUiState.Loading)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = JokeUiState.Loading
    )

    fun getJoke() = viewModelScope.launch {
        val response = try {
            val response = jokesRepository.getJoke()
            if (response.joke.isNullOrEmpty() || response.error) {
                _uiState.update {
                    JokeUiState.Error("Joke is not available")
                }
            } else {
                _uiState.update {
                    JokeUiState.Success(response.joke, response.id)
                }
            }
        } catch (e: Exception) {
            _uiState.update {
                JokeUiState.Error("Something went wrong")
            }
        }
    }

    fun getNextJoke() = viewModelScope.launch {
        val response = try {
            val response = jokesRepository.getNextJoke()
            if (response.joke.isNullOrEmpty() || response.error) {
                _uiState.update {
                    JokeUiState.Error("Joke is not available")
                }
            } else {
                _uiState.update {
                    JokeUiState.Success(response.joke, response.id)
                }
            }
        } catch (e: Exception) {
            _uiState.update {
                JokeUiState.Error("Something went wrong")
            }
        }
    }

    fun markFavourite(id: Long) = viewModelScope.launch {
        jokesRepository.markJokeAsFavourite(id)
    }
}