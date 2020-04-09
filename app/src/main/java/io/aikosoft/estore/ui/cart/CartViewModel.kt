package io.aikosoft.estore.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.aikosoft.estore.base.BaseViewModel
import io.aikosoft.estore.data.repositories.UserRepository
import io.aikosoft.estore.models.CartProducts
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _cartProducts = MutableLiveData<CartProducts>()

    val cartProducts: LiveData<CartProducts> get() = _cartProducts

    fun fetchCartProducts() {
        userRepository.getCartProducts().request {
            _cartProducts.value = it
        }
    }
}