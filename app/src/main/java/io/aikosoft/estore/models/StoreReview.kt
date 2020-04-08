package io.aikosoft.estore.models

import java.util.*

typealias StoreReviews = List<StoreReview>

data class StoreReview(
    val id: Int,
    val reviewerName: String,
    val reviewerAvatarUrl: String?,
    val reviewedDate: Date,
    val rating: Double,
    val reviewMessage: String
)