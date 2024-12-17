package com.example.interviewprepsample.strategy

class PaymentGateway(private var paymentStrategy: PaymentStrategy) {
    fun pay(amount: Double) {
        paymentStrategy.pay(amount)
    }

    fun setPaymentStrategy(paymentStrategy: PaymentStrategy) {
        this.paymentStrategy = paymentStrategy
    }
}