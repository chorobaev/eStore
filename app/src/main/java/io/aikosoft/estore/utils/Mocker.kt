package io.aikosoft.estore.utils

import io.aikosoft.estore.models.Product
import io.aikosoft.estore.models.Products
import io.aikosoft.estore.models.StoreReview
import io.reactivex.Single
import java.util.*
import kotlin.collections.ArrayList

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
            Thread.sleep(100)
            it.onSuccess(products)
        }

    @get:SuppressWarnings
    val storeReviews: List<StoreReview>
        get() {
            val reviews = ArrayList<StoreReview>()
            for (i in 1..3) {
                reviews.add(
                    StoreReview(
                        id = i,
                        reviewer = listOf("Maxim", "Alex", "Tom")[i - 1],
                        reviewedDate = Date(),
                        rating = 3.5 + 0.5 * i,
                        reviewMessage = "If you are building social network or chat app then " +
                                "this post is really helpful. Logic behind this thing is " +
                                "very simple when ever user post data we set time GMT"
                    )
                )
            }
            return reviews
        }

    val singleStoreReviews: Single<List<StoreReview>>
        get() = Single.create {
            Thread.sleep(100)
            it.onSuccess(storeReviews)
        }

    fun <T> getSingleEmptyList(): Single<List<T>> {
        return Single.create {
            it.onSuccess(emptyList())
        }
    }
}