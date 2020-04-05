package io.aikosoft.estore.models

typealias Products = List<Product>

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val sellInfo: String,
    val discount: Int,
    val imageUrl: String,
    val isAlmostGone: Boolean = false
)