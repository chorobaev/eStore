package io.aikosoft.estore.utils

import io.aikosoft.estore.models.Product
import io.aikosoft.estore.models.Products
import io.reactivex.Single

object Mocker {

    @get:SuppressWarnings
    val products: Products
        get() {
            val products = ArrayList<Product>()
            for (i in 1..10) {
                products.add(
                    Product(
                        id = i,
                        name = "Apple",
                        price = 100.0 + 7 * i,
                        sellInfo = "1000+ bought",
                        discount = if (i % 3 == 0) 0 else 10 + i,
                        imageUrl = "",
                        isAlmostGone = i % 5 == 0
                    )
                )
            }
            return products
        }

    val singleProducts: Single<Products>
        get() = Single.create {
            Thread.sleep(1000)
            it.onSuccess(products)
        }
}