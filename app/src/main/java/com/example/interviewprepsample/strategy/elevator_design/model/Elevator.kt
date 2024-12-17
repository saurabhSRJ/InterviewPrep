package com.example.interviewprepsample.strategy.elevator_design.model

import java.util.PriorityQueue

class Elevator(private val capacity: Int, private val id: Int) {
    var currentFloor: Int = 0
    var currentDirection: Direction = Direction.IDLE
    private val downRequests =
        PriorityQueue<Request> { a, b -> b.destination - a.destination } //max heap
    private val upRequest =
        PriorityQueue<Request> { a, b -> a.destination - b.destination } //min heap

    @Synchronized
    fun addRequest(request: Request) {
        if (request.location == Location.OUTSIDE) {
            if (request.destination > this.currentFloor) {
                sendUpRequest(request)
            } else {
                sendDownRequest(request)
            }
        } else if (request.location == Location.INSIDE) {
            if (request.source > request.destination) {
                sendDownRequest(request)
            } else {
                sendUpRequest(request)
            }
        }
    }

    private fun sendUpRequest(request: Request) {
        if (request.location == Location.OUTSIDE) {
            upRequest.add(Request(request.source, request.source, Direction.UP, Location.OUTSIDE))
            println("Adding an up request to pickup the passenger at floor: ${request.source}")
        }
        upRequest.add(request)
        println("Adding an up request to drop the passenger at floor: ${request.destination}")
    }

    private fun sendDownRequest(request: Request) {
        if (request.location == Location.OUTSIDE) {
            downRequests.add(
                Request(
                    request.source,
                    request.source,
                    Direction.UP,
                    Location.OUTSIDE
                )
            )
            println("Adding a down request to pickup the passenger at floor: ${request.source}")
        }
        downRequests.add(request)
        println("Adding a down request to drop the passenger at floor: ${request.destination}")
    }

    fun run() {
        while (!upRequest.isEmpty() || !downRequests.isEmpty()) {
            processRequests()
        }
        println("Finished all request")
        this.currentDirection = Direction.IDLE
    }

    private fun processRequests() {
        if (this.currentDirection == Direction.UP || this.currentDirection == Direction.IDLE) {
            processUpRequests()
            processDownRequests()
        } else {
            processDownRequests()
            processUpRequests()
        }
    }

    private fun processUpRequests() {
        while (!upRequest.isEmpty()) {
            val request = upRequest.poll()
            this.currentFloor = request.destination
            println("moving to floor: ${request.destination}")
        }
        if (downRequests.isEmpty()) {
            this.currentDirection = Direction.IDLE
        } else {
            this.currentDirection = Direction.DOWN
        }
    }

    private fun processDownRequests() {
        while (!downRequests.isEmpty()) {
            val request = downRequests.poll()
            this.currentFloor = request.destination
            println("moving to floor: ${request.destination}")
        }
        if (upRequest.isEmpty()) {
            this.currentDirection = Direction.IDLE
        } else {
            this.currentDirection = Direction.DOWN
        }
    }
}