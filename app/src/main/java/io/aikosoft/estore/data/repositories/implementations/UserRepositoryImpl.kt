package io.aikosoft.estore.data.repositories.implementations

import io.aikosoft.estore.data.network.UserClient
import io.aikosoft.estore.data.repositories.UserRepository
import io.aikosoft.estore.models.CartProducts
import io.aikosoft.estore.utils.Mocker
import io.reactivex.Single

class UserRepositoryImpl(
    private val userClient: UserClient
) : UserRepository {

    override fun getCartProducts(): Single<CartProducts> {
        return Mocker.singleCartProducts
    }
}