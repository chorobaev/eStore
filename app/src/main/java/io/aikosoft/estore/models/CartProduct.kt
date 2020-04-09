package io.aikosoft.estore.models

typealias CartProducts = List<CartProduct>

data class CartProduct(
    val id: Int,
    val name: String,
    val color: String? = null,
    val bulk: String? = null,
    val extra: String? = null
)