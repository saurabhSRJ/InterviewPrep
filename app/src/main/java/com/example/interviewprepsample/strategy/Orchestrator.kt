package com.example.interviewprepsample.strategy

import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val upiPaymentStrategy = UPIPaymentStrategy("abc@bank.com")
    val paymentGateway = PaymentGateway(upiPaymentStrategy)
    paymentGateway.pay(20.0)

    val netBankingPaymentStrategy = NetBankingPaymentStrategy("dummy-account-number")
    paymentGateway.setPaymentStrategy(netBankingPaymentStrategy)
    paymentGateway.pay(20.0)
}
