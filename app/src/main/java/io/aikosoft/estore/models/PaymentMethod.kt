package io.aikosoft.estore.models

typealias PaymentMethods = List<PaymentMethod>

data class PaymentMethod(
    val id: Int,
    val name: String
)