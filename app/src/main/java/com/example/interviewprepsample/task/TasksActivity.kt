package com.example.interviewprepsample.task

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewprepsample.databinding.ActivityTasksBinding
import com.example.interviewprepsample.mars.model.MarsContent
import com.example.interviewprepsample.mars.model.MarsHeaderItem
import com.example.interviewprepsample.mars.model.UiItem
import com.example.interviewprepsample.task.data.repository.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.UUID

//@AndroidEntryPoint
//class TasksActivity: AppCompatActivity() {
//    private lateinit var binding: ActivityTasksBinding
//    private val tasksViewModel by viewModels<TasksViewModel>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityTasksBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//    }
//
//    private fun getData() {
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.RESUMED) {
//                tasksViewModel.uiState.collect { state ->
//                    when (state) {
//                        is TasksUiState.Error -> handleError()
//                        is TasksUiState.Loading -> handleLoading()
//                        is TasksUiState.Success -> handleSuccess(state.tasks)
//                    }
//                }
//            }
//        }
//    }
//
//    private fun setUpViews() {
//        binding.run {
//            rvContent.layoutManager = LinearLayoutManager(this@TasksActivity)
//            rvContent.adapter = marsAdapter
//        }
//    }
//
//    private fun handleSuccess(response: List<Task>) {
//        val list = mutableListOf<UiItem>().apply {
//            add(
//                MarsHeaderItem(
//                    id = UUID.randomUUID().toString(),
//                    title = "Total items: ${response.size}"
//                )
//            )
//        }
//        list.addAll(response.map { MarsContent(it.id, it.img_src) })
//
//        marsAdapter.submitList(list)
//        binding.rvContent.visibility = View.VISIBLE
//        binding.tvTitle.visibility = View.GONE
//    }
//
//    private fun handleError() {
//        binding.tvTitle.text = "Error Loading Data"
//    }
//
//    private fun handleLoading() {
//        binding.tvTitle.text = "Loading"
//    }}