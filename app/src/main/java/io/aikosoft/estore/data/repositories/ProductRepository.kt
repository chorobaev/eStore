package io.aikosoft.estore.data.repositories

import io.aikosoft.estore.models.Products
import io.reactivex.Single

interface ProductRepository {

    fun getPopularProducts(): Single<Products>
}