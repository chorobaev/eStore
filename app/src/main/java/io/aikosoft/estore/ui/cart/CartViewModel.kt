package io.aikosoft.estore.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.aikosoft.estore.base.BaseViewModel
import io.aikosoft.estore.data.repositories.UserRepository
import io.aikosoft.estore.models.CartProduct
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
    private val _loadingCartProducts = MutableLiveData<Boolean>()
    private val _shouldConfirmRemoval = SingleLiveEvent<CartProduct>()
    private val _checkoutSuccess = SingleLiveEvent<Unit>()

    var selectedCartProduct: CartProduct? = null

    val cartProducts: LiveData<CartProducts> get() = _cartProducts
    val paymentMethod: LiveData<PaymentMethod> get() = _paymentMethod
    val shippingAddress: LiveData<ShippingAddress> get() = _shippingAddress
    val loadingCartProducts: LiveData<Boolean> get() = _loadingCartProducts
    val shouldConfirmProductRemoval: LiveData<CartProduct> get() = _shouldConfirmRemoval
    val checkoutSuccess: LiveData<Unit> get() = _checkoutSuccess

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
        _loadingCartProducts.value = true
        userRepository.getCartProducts()
            .doFinally {
                _loadingCartProducts.postValue(false)
            }
            .request {
                _cartProducts.value = it
            }
    }

    fun changeProductQuantity(newQuantity: Int) {
        if (newQuantity == 0) {
            _shouldConfirmRemoval.value = selectedCartProduct
        } else {
            setProductQuantity(newQuantity)
        }
    }

    fun removeProduct() {
        setProductQuantity(newQuantity = 0)
    }

    private fun setProductQuantity(newQuantity: Int) {
        val productId = selectedCartProduct?.id ?: return

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

    fun checkout() {
        userRepository.checkout().request {
            _checkoutSuccess.call()
        }
    }
}
