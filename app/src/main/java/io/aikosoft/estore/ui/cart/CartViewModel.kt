package io.aikosoft.estore.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.aikosoft.estore.base.BaseViewModel
import io.aikosoft.estore.data.repositories.UserRepository
import io.aikosoft.estore.models.CartProducts
import io.aikosoft.estore.models.PaymentMethod
import io.aikosoft.estore.models.ShippingAddress
import io.aikosoft.estore.utils.SingleLiveEvent
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _cartProducts = MutableLiveData<CartProducts>()
    private val _paymentMethod = MutableLiveData<PaymentMethod>()
    private val _shippingAddress = MutableLiveData<ShippingAddress>()
    private val _shouldSelectQuantity = SingleLiveEvent<Int>()

    var selectedCartProductId: Int? = null

    val cartProducts: LiveData<CartProducts> get() = _cartProducts
    val paymentMethod: LiveData<PaymentMethod> get() = _paymentMethod
    val shippingAddress: LiveData<ShippingAddress> get() = _shippingAddress
    val shouldSelectQuantity: LiveData<Int> get() = _shouldSelectQuantity

    fun fetchData() {
        fetchPaymentMethod()
        fetchShippingAddress()
        fetchCartProducts()
    }

    private fun fetchPaymentMethod() {
        userRepository.getPaymentMethod().request {
            _paymentMethod.value = it
        }
    }

    private fun fetchShippingAddress() {
        userRepository.getShippingAddress().request {
            _shippingAddress.value = it
        }
    }

    private fun fetchCartProducts() {
        userRepository.getCartProducts().request {
            _cartProducts.value = it
        }
    }

    fun changeProductQuantity(newQuantity: Int) {
        val productId = selectedCartProductId ?: return

        userRepository.changeProductQuantity(productId, newQuantity).request {
            _cartProducts.value = cartProducts.value?.asSequence()
                ?.map { cartProduct ->
                    if (cartProduct.id == productId)
                        cartProduct.copy(quantity = newQuantity)
                    else
                        cartProduct
                }
                ?.filter { cartProduct ->
                    newQuantity != 0 || cartProduct.id != productId
                }
                ?.toList()
        }
    }
}
