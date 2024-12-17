package com.example.interviewprepsample.mars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_data.mars.MarsPhoto
import com.example.interviewprepsample.mars.repository.MarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MarsUiState {
    data object Loading : MarsUiState()
    data class Error(val message: String?) : MarsUiState()
    data class Success(val tasks: List<MarsPhoto>) : MarsUiState()
}

@HiltViewModel
class MarsViewModel @Inject constructor(
    private val marsRepository: MarsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<MarsUiState>(MarsUiState.Loading)
//        marsRepository.getPhotos().map { photos ->
//        if (photos.isEmpty()) {
//            MarsUiState.Error("Error getting photos")
//        } else {
//            MarsUiState.Success(photos)
//        }
//    }.catch { e->
//        emit(MarsUiState.Error(e.message))
//    }

    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MarsUiState.Loading
    )

    fun getData() = viewModelScope.launch {
        val response = marsRepository.getPhotosNormal()
        if (response.isEmpty()) {
            _uiState.update {
                MarsUiState.Error("")
            }
        } else {
            _uiState.update { MarsUiState.Success(tasks = response) }
        }
    }
}