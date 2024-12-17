package com.example.interviewprepsample.mars

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_data.mars.MarsPhoto
import com.example.interviewprepsample.R
import com.example.interviewprepsample.databinding.ActivityMainBinding
import com.example.interviewprepsample.mars.adapter.MarsAdapter
import com.example.interviewprepsample.mars.model.MarsContent
import com.example.interviewprepsample.mars.model.MarsHeaderItem
import com.example.interviewprepsample.mars.model.UiItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID

@AndroidEntryPoint
class MarsActivity : AppCompatActivity() {
    private val marsViewModel by viewModels<MarsViewModel>()
    private lateinit var binding: ActivityMainBinding
    private val marsAdapter: MarsAdapter by lazy {
        MarsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpViews()
        getData()
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.async {
            async {
                throw Exception("Exception testing")
            }
        }
    }

    private fun getData() {
        marsViewModel.getData()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                marsViewModel.uiState.collectLatest { state ->
                    when (state) {
                        is MarsUiState.Success -> {
                            handleSuccess(state.tasks)
                        }

                        is MarsUiState.Error -> handleError(state.message)
                        MarsUiState.Loading -> handleLoading()
                    }
                }
            }
        }
    }

    private fun setUpViews() {
        binding.run {
            rvContent.layoutManager = LinearLayoutManager(this@MarsActivity)
            rvContent.adapter = marsAdapter
        }
    }

    private fun handleSuccess(response: List<MarsPhoto>) {
        val list = mutableListOf<UiItem>().apply {
            add(
                MarsHeaderItem(
                    id = UUID.randomUUID().toString(),
                    title = "Total items: ${response.size}"
                )
            )
        }
        list.addAll(response.map { MarsContent(it.id, it.img_src) })

        marsAdapter.submitList(list)
        binding.rvContent.visibility = View.VISIBLE
        binding.tvTitle.visibility = View.GONE
    }

    private fun handleError(message: String?) {
        binding.tvTitle.text = if (message.isNullOrEmpty()) "Error Loading Data" else message
    }

    private fun handleLoading() {
        binding.tvTitle.text = "Loading"
    }
}