package io.aikosoft.estore.models

data class Store(
    val id: Int,
    val name: String,
    val rating: Float,
    val ratedCount: Int,
    val avatarUrl: String? = null
)