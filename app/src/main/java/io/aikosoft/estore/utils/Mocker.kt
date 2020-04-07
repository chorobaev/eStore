package io.aikosoft.estore.utils

import io.aikosoft.estore.models.Product
import io.aikosoft.estore.models.Products
import io.aikosoft.estore.models.Store
import io.aikosoft.estore.models.StoreReview
import io.reactivex.Single
import java.util.*
import kotlin.collections.ArrayList

object Mocker {

    private const val DELAY = 100L

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
                        description = "I put it in my source code because we want to support lower " +
                                "API levels. It is working but the infinite " +
                                "loop is not a good practice.",
                        rating = 3 + 0.2 * i,
                        ratedCount = 10 + 5 * i,
                        discount = if (i % 3 == 0) 0 else 10 + i,
                        imageUrls = listOf("", ""),
                        isAlmostGone = i % 5 == 0
                    )
                )
            }
            return products
        }

    val singleProducts: Single<Products>
        get() = Single.create {
            Thread.sleep(DELAY)
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
                        reviewerName = listOf("Maxim", "Alex", "Tom")[i - 1],
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
            Thread.sleep(DELAY)
            it.onSuccess(storeReviews)
        }

    @get:SuppressWarnings
    val store: Store
        get() {
            return Store(
                id = 1,
                name = "eStore",
                rating = 4.5F,
                ratedCount = 105
            )
        }

    val singleStore: Single<Store>
    get() = Single.create {
        Thread.sleep(DELAY)
        it.onSuccess(store)
    }

    fun <T> getSingleEmptyList(): Single<List<T>> {
        return Single.create {
            it.onSuccess(emptyList())
        }
    }
}