package io.aikosoft.estore.utils

import io.aikosoft.estore.models.*
import io.reactivex.Single
import java.util.*
import kotlin.collections.ArrayList

object Mocker {

    private const val DELAY = 2000L
    @get:SuppressWarnings
    val imageUrls: List<String>
        get() = listOf(
            "https://www.virginmedia.com/content/dam/virginmedia/dotcom/images" +
                    "/mobile-browse/Apple/iPhone-Xr/blue/iphone-xr-blue-front.png",
            "https://cdn11.bigcommerce.com/s-fowty94aox/product_images/uploaded_images/iphone-xr.png"
        )

    @get:SuppressWarnings
    val avatarUrls: List<String?>
        get() = listOf(
            "https://filmschoolrejects.com/wp-content/" +
                    "uploads/2017/04/0JRofTsuy93evl_J5.jpg",
            "https://i.pinimg.com/originals/a4/4a/f3/a44af3bb5f074e3cdb4be8a56232c996.jpg"
        )

    @get:SuppressWarnings
    val colors: List<String>
        get() = listOf(
            "Black", "White", "Red", "Transparent"
        )

    val singleString: Single<String>
        get() = Single.create {
            it.onSuccess("\"msg\": \"success\"")
        }

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
                        imageUrls = imageUrls.shuffled(),
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
                        reviewerAvatarUrl = avatarUrls.random(),
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
                ratedCount = 105,
                avatarUrl = avatarUrls.random()
            )
        }

    val singleUnit: Single<Unit>
        get() = Single.create {
            Thread.sleep(DELAY)
            it.onSuccess(Unit)
        }

    val singleStore: Single<Store>
        get() = Single.create {
            Thread.sleep(DELAY)
            it.onSuccess(store)
        }

    @get:SuppressWarnings
    val cartProducts: CartProducts
        get() {
            val data = ArrayList<CartProduct>()
            for (i in 1..5) {
                data.add(
                    CartProduct(
                        id = i,
                        name = "Item $i",
                        imageUrl = imageUrls.random(),
                        color = colors.random(),
                        bulk = "Bulk ${10 * i}",
                        extra = "Apr 14 - Jun 8",
                        discountedPrice = 4.0,
                        actualPrice = 5.0,
                        shippingPrice = 1.0,
                        quantity = 1
                    )
                )
            }
            return data
        }

    val singleCartProducts: Single<CartProducts>
        get() = Single.create {
            Thread.sleep(DELAY)
            it.onSuccess(cartProducts)
        }

    @get:SuppressWarnings
    val shippingAddress: ShippingAddress
        get() {
            return ShippingAddress(
                id = 1,
                firstName = "Tom",
                lastName = "Carter",
                firstAddressLine = "Address 1",
                secondAddressLine = "Address 2",
                country = "USA",
                stateOrProvince = "Washington",
                city = "Washington",
                zipPostalCode = "123456",
                phoneNumber = "+996 222 123123"
            )
        }

    val singleShippingAddress: Single<ShippingAddress>
        get() = Single.create {
            Thread.sleep(DELAY)
            it.onSuccess(shippingAddress)
        }

    @get:SuppressWarnings
    val paymentMethod: PaymentMethod
        get() {
            return PaymentMethod(
                id = 1,
                name = "VISA 44** **45"
            )
        }

    val singlePaymentMethod: Single<PaymentMethod>
        get() = Single.create {
            Thread.sleep(DELAY)
            it.onSuccess(paymentMethod)
        }

    fun <T> getSingleEmptyList(): Single<List<T>> {
        return Single.create {
            it.onSuccess(emptyList())
        }
    }
}