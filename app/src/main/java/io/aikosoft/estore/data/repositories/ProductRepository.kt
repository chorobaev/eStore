package io.aikosoft.estore.data.repositories

import io.aikosoft.estore.models.Products
import io.aikosoft.estore.models.Store
import io.aikosoft.estore.models.StoreReviews
import io.reactivex.Single

interface ProductRepository {

    fun getPopularProducts(): Single<Products>

    fun getStoreReviewsByProductId(productId: Int): Single<StoreReviews>

    fun getStoreByProductId(productId: Int): Single<Store>

    fun addProductToCart(productId: Int): Single<Unit>
}