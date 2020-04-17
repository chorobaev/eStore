package io.aikosoft.estore.data.repositories.implementations

import io.aikosoft.estore.data.network.UserClient
import io.aikosoft.estore.data.repositories.UserRepository
import io.aikosoft.estore.models.CartProduct
import io.aikosoft.estore.models.CartProducts
import io.aikosoft.estore.models.PaymentMethod
import io.aikosoft.estore.models.ShippingAddress
import io.aikosoft.estore.utils.Mocker
import io.reactivex.Single

class UserRepositoryImpl(
    private val userClient: UserClient
) : UserRepository {

    private var snapshotProducts: CartProducts? = null

    override fun getCartProducts(): Single<CartProducts> {
        return Mocker.singleCartProducts.doOnSuccess {
            snapshotProducts = it
        }
    }

    override fun getPaymentMethod(): Single<PaymentMethod> {
        return Mocker.singlePaymentMethod
    }

    override fun getShippingAddress(): Single<ShippingAddress> {
        return Mocker.singleShippingAddress
    }

    override fun changeProductQuantity(productId: Int, newQuantity: Int): Single<CartProduct> {
        return Single.create { emitter ->
            val result = snapshotProducts?.find {
                it.id == productId
            }

            if (result != null) {
                emitter.onSuccess(result)
            } else {
                emitter.onError(Throwable("{\"msg\": \"Product not found!\"}"))
            }
        }
    }

    override fun checkout(): Single<String> {
        return Mocker.singleString
    }
}