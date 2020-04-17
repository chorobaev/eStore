package io.aikosoft.estore.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.aikosoft.estore.base.BaseViewModel
import io.aikosoft.estore.data.repositories.ProductRepository
import io.aikosoft.estore.models.Product
import io.aikosoft.estore.models.Store
import io.aikosoft.estore.models.StoreReviews
import io.aikosoft.estore.utils.SingleLiveEvent
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    private val _product = MutableLiveData<Product>()
    private val _storeReviews = MutableLiveData<StoreReviews>()
    private val _store = MutableLiveData<Store>()
    private val _productAddingSuccess = SingleLiveEvent<Unit>()
    private val _loadingStoreReviews = MutableLiveData<Boolean>()
    private val _loadingStore = MutableLiveData<Boolean>()

    val product: LiveData<Product> get() = _product
    val storeReviews: LiveData<StoreReviews> get() = _storeReviews
    val store: LiveData<Store> get() = _store
    val productAddingToCartSuccess: LiveData<Unit> get() = _productAddingSuccess
    val loadingStoreReviews: LiveData<Boolean> get() = _loadingStoreReviews
    val loadingStore: LiveData<Boolean> get() = _loadingStore

    private val productId: Int
        get() = _product.value?.id ?: throw IllegalArgumentException("Product must not be null")

    fun setProduct(product: Product) {
        _product.value = product
        fetchAdditionalData()
    }

    private fun fetchAdditionalData() {
        fetchStoreReviews(productId)
        fetchStore(productId)
    }

    private fun fetchStoreReviews(productId: Int) {
        _loadingStoreReviews.value = true
        productRepository.getStoreReviewsByProductId(productId)
            .doFinally {
                _loadingStoreReviews.postValue(false)
            }
            .request {
                _storeReviews.value = it
            }
    }

    private fun fetchStore(productId: Int) {
        _loadingStore.value = true
        productRepository.getStoreByProductId(productId)
            .doFinally {
                _loadingStore.postValue(false)
            }
            .request {
                _store.value = it
            }
    }

    fun addProductToCart() {
        productRepository.addProductToCart(productId).request {
            _productAddingSuccess.call()
        }
    }
}