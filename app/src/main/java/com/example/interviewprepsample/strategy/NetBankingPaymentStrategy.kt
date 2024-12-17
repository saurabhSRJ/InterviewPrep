package com.example.interviewprepsample.strategy

class NetBankingPaymentStrategy(private val accountNumber: String): PaymentStrategy {
    private val chargePercentage: Double = 5.0
    override fun pay(amount: Double) {
        println("Paid $amount Rs through net banking linked to account number: $accountNumber")
        println("gateway charges for payment: ${calculateGatewayCharge(amount)}")
    }

    private fun calculateGatewayCharge(amount: Double): Double {
        return amount * chargePercentage / 100
    }
}