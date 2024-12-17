package com.example.interviewprepsample.strategy

class UPIPaymentStrategy(private val upiId: String): PaymentStrategy {
    private val chargePercentage = 0
    override fun pay(amount: Double) {
        println("Paid $amount Rs through UPI to upi id: $upiId")
        println("gateway charges for payment: ${calculateGatewayCharge(amount)}")
    }

    private fun calculateGatewayCharge(amount: Double): Double {
        return amount * chargePercentage / 100
    }
}