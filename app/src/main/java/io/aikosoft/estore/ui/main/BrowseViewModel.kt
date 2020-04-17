package io.aikosoft.estore.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.aikosoft.estore.base.BaseViewModel
import io.aikosoft.estore.data.repositories.ProductRepository
import io.aikosoft.estore.models.BrowsePageType
import io.aikosoft.estore.models.Products
import javax.inject.Inject

class BrowseViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    private val _products = MutableLiveData<Products>()
    private val _productsLoading = MutableLiveData<Boolean>()

    val products: LiveData<Products> get() = _products
    val productsLoading: LiveData<Boolean> get() = _productsLoading

    fun fetchProducts(pageType: BrowsePageType) {
        when (pageType) {
            BrowsePageType.Popular -> fetchPopularProducts()
            BrowsePageType.RecentlyViewed -> Unit
        }
    }

    private fun fetchPopularProducts() {
        _productsLoading.value = true
        productRepository.getPopularProducts()
            .doFinally {
                _productsLoading.postValue(false)
            }
            .request {
                _products.value = it
            }
    }
}