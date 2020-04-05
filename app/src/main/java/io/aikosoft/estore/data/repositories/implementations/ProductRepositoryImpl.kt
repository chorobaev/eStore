package io.aikosoft.estore.data.repositories.implementations

import io.aikosoft.estore.data.repositories.ProductRepository
import io.aikosoft.estore.models.Products
import io.aikosoft.estore.utils.Mocker
import io.reactivex.Single

class ProductRepositoryImpl() : ProductRepository {

    override fun getPopularProducts(): Single<Products> {
        return Mocker.singleProducts
    }
}