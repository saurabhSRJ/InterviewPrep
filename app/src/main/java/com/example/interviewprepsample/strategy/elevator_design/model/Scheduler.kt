package com.example.interviewprepsample.strategy.elevator_design.model

import com.example.interviewprepsample.strategy.elevator_design.selection_strategy.ElevatorSelectionStrategy

class Scheduler(private val selectionStrategy: ElevatorSelectionStrategy) {
    private val requests = mutableListOf<Request>()

    fun addRequest(request: Request) {
//        selectionStrategy.assign()
    }
}