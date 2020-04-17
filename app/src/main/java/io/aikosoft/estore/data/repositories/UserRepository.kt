package io.aikosoft.estore.data.repositories

import io.aikosoft.estore.models.CartProduct
import io.aikosoft.estore.models.CartProducts
import io.aikosoft.estore.models.PaymentMethod
import io.aikosoft.estore.models.ShippingAddress
import io.reactivex.Single

interface UserRepository {

    fun getCartProducts(): Single<CartProducts>

    fun getPaymentMethod(): Single<PaymentMethod>

    fun getShippingAddress(): Single<ShippingAddress>

    fun changeProductQuantity(productId: Int, newQuantity: Int): Single<CartProduct>

    fun checkout(): Single<String>
}