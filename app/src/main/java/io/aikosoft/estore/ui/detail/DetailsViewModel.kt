package io.aikosoft.estore.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.aikosoft.estore.base.BaseViewModel
import io.aikosoft.estore.data.repositories.ProductRepository
import io.aikosoft.estore.models.Product
import io.aikosoft.estore.models.StoreReviews
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    var product: Product? = null
        set(value) {
            field = value
            value?.let { fetchStoreReviews(value.id) }
        }

    private val _storeReviews = MutableLiveData<StoreReviews>()

    val storeReviews: LiveData<StoreReviews> get() = _storeReviews

    private fun fetchStoreReviews(productId: Int) {
        productRepository.getStoreReviewsByProductId(productId).request {
            _storeReviews.value = it
        }
    }
}