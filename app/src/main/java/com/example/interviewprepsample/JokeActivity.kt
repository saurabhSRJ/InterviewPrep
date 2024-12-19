package com.example.interviewprepsample

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.interviewprepsample.databinding.ActivityJokeBinding
import com.example.interviewprepsample.ui.JokeUiState
import com.example.interviewprepsample.ui.JokeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JokeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityJokeBinding
    private val jokeViewModel by viewModels<JokeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpClickListener()
        getData()
    }

    private fun setUpClickListener() {
        binding.run {
            btnNext.setOnClickListener {
                jokeViewModel.getNextJoke()
            }
        }
    }

    private fun getData() {
        jokeViewModel.getJoke()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                jokeViewModel.uiState.collect { state ->
                    when (state) {
                        is JokeUiState.Success -> {
                            handleSuccess(state.joke, state.id)
                        }

                        is JokeUiState.Error -> handleError(state.message)
                        JokeUiState.Loading -> handleLoading()
                    }
                }
            }
        }
    }

    private fun handleSuccess(joke: String, id: Long) {
        binding.tvMessage.visibility = View.GONE
        binding.tvJoke.text = joke
        binding.btnFavourites.setOnClickListener {
            jokeViewModel.markFavourite(id)
        }
    }

    private fun handleError(message: String?) {
        binding.tvMessage.text = if (message.isNullOrEmpty()) "Error Loading Data" else message
    }

    private fun handleLoading() {
        binding.tvMessage.text = "Loading"
    }
}