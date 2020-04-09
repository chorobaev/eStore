package io.aikosoft.estore.data.repositories

import io.aikosoft.estore.models.CartProducts
import io.reactivex.Single

interface UserRepository {

    fun getCartProducts(): Single<CartProducts>
}