package com.example.interviewprepsample.task

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewprepsample.common.CommonUiState
import com.example.interviewprepsample.common.loadingUiState
import com.example.interviewprepsample.task.data.repository.Task
import com.example.interviewprepsample.task.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TasksUiState(
    val state: CommonUiState = CommonUiState(),
    val tasks: List<Task> = emptyList()
)

const val TASKS_FILTER_SAVED_STATE_KEY = "TASKS_FILTER_SAVED_STATE_KEY"

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _tasksFilterType = savedStateHandle.getStateFlow(TASKS_FILTER_SAVED_STATE_KEY,TasksFilterType.ALL_TASKS)
    private val _filteredTasks = combine(taskRepository.getTasksStream(), _tasksFilterType) { tasks, tasksFilterType->

    }
    private val _uiState = MutableStateFlow(TasksUiState(state = loadingUiState))

    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TasksUiState(state = loadingUiState)
    )

    fun getTasks() = viewModelScope.launch {
        val response = taskRepository.getTasks()
    }
}