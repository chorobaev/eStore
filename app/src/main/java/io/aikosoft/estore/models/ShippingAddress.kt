package io.aikosoft.estore.models

typealias ShippingAddresses = List<ShippingAddress>

data class ShippingAddress(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val firstAddressLine: String,
    val secondAddressLine: String,
    val country: String,
    val stateOrProvince: String,
    val city: String,
    val zipPostalCode: String,
    val phoneNumber: String
)