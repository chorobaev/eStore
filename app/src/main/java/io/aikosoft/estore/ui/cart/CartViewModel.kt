package io.aikosoft.estore.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.aikosoft.estore.base.BaseViewModel
import io.aikosoft.estore.data.repositories.UserRepository
import io.aikosoft.estore.models.CartProducts
import io.aikosoft.estore.models.PaymentMethod
import io.aikosoft.estore.models.ShippingAddress
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _cartProducts = MutableLiveData<CartProducts>()
    private val _paymentMethod = MutableLiveData<PaymentMethod>()
    private val _shippingAddress = MutableLiveData<ShippingAddress>()

    val cartProducts: LiveData<CartProducts> get() = _cartProducts
    val paymentMethod: LiveData<PaymentMethod> get() = _paymentMethod
    val shippingAddress: LiveData<ShippingAddress> get() = _shippingAddress

    fun fetchData() {
        fetchPaymentMethod()
        fetchShippingAddress()
        fetchCartProducts()
    }

    private fun fetchPaymentMethod() {
        userRepository.getPaymentMathod().request {
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
}
