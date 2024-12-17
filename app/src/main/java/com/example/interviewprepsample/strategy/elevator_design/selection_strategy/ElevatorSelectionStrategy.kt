package com.example.interviewprepsample.strategy.elevator_design.selection_strategy

import com.example.interviewprepsample.strategy.elevator_design.model.Request

interface ElevatorSelectionStrategy {
    fun assign(request: Request)
}