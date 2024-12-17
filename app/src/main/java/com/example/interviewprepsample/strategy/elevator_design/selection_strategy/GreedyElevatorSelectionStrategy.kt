package com.example.interviewprepsample.strategy.elevator_design.selection_strategy

import com.example.interviewprepsample.strategy.elevator_design.model.Direction
import com.example.interviewprepsample.strategy.elevator_design.model.Elevator
import com.example.interviewprepsample.strategy.elevator_design.model.Request
import kotlin.math.abs

class GreedyElevatorSelectionStrategy(private val capacity: Int, private val numElevator: Int) :
    ElevatorSelectionStrategy {
    private val elevators = mutableListOf<Elevator>()

    init {
        for (i in 0..numElevator) {
            val elevator = Elevator(capacity, i + 1)
            elevators.add(elevator)
            Thread(elevator::run).start()
        }
    }

    override fun assign(request: Request) {
        val optimalElevator = findOptimalElevator(request.source, request.destination)
        optimalElevator?.addRequest(request)
    }

    private fun findOptimalElevator(sourceFloor: Int, destinationFloor: Int): Elevator? {
        var optimalElevator: Elevator? = null
        var minDistance = Int.MAX_VALUE
        val direction = if (sourceFloor > destinationFloor) Direction.DOWN else Direction.UP

        for (elevator in elevators.filter { it.currentDirection == direction }) {
            val distance = abs((sourceFloor - elevator.currentFloor).toDouble()).toInt()
            if (distance < minDistance) {
                minDistance = distance
                optimalElevator = elevator
            }
        }
        return optimalElevator
    }
}