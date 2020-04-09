package io.aikosoft.estore.data.repositories.implementations

import io.aikosoft.estore.data.network.ProductClient
import io.aikosoft.estore.data.repositories.ProductRepository
import io.aikosoft.estore.models.Products
import io.aikosoft.estore.models.Store
import io.aikosoft.estore.models.StoreReviews
import io.aikosoft.estore.utils.Mocker
import io.reactivex.Single

class ProductRepositoryImpl(
    private val productClient: ProductClient
) : ProductRepository {

    override fun getPopularProducts(): Single<Products> {
        return Mocker.singleProducts
    }

    override fun getStoreReviewsByProductId(productId: Int): Single<StoreReviews> {
        return Mocker.singleStoreReviews
    }

    override fun getStoreByProductId(productId: Int): Single<Store> {
        return Mocker.singleStore
    }

    override fun addProductToCart(productId: Int): Single<Unit> {
        return Mocker.singleUnit
    }
}