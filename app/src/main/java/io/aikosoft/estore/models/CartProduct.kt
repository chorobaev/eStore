package io.aikosoft.estore.models

typealias CartProducts = List<CartProduct>

data class CartProduct(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val discountedPrice: Double,
    val actualPrice: Double,
    val shippingPrice: Double,
    val quantity: Int,
    val bulk: String? = null,
    val color: String? = null,
    val extra: String? = null
) {
    val totalActualPrice: Double
        get() = quantity * actualPrice

    val totalDiscountedPrice: Double
        get() = quantity * discountedPrice
}