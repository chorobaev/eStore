package io.aikosoft.estore.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.aikosoft.estore.base.BaseViewModel
import io.aikosoft.estore.data.repositories.ProductRepository
import io.aikosoft.estore.models.Product
import io.aikosoft.estore.models.Store
import io.aikosoft.estore.models.StoreReviews
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    private val _product = MutableLiveData<Product>()
    private val _storeReviews = MutableLiveData<StoreReviews>()
    private val _store = MutableLiveData<Store>()

    val product: LiveData<Product> get() = _product
    val storeReviews: LiveData<StoreReviews> get() = _storeReviews
    val store: LiveData<Store> get() = _store

    fun setProduct(product: Product) {
        _product.value = product
    }

    fun fetchAdditionalData(productId: Int) {
        fetchStoreReviews(productId)
        fetchStore(productId)
    }

    fun fetchStoreReviews(productId: Int) {
        productRepository.getStoreReviewsByProductId(productId).request {
            _storeReviews.value = it
        }
    }

    fun fetchStore(productId: Int) {
        productRepository.getStoreByProductId(productId).request {
            _store.value = it
        }
    }
}