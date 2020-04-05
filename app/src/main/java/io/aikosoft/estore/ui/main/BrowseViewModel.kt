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

    val products: LiveData<Products> get() = _products

    fun fetchProducts(pageType: BrowsePageType) {
        when (pageType) {
            BrowsePageType.Popular -> fetchPopularProducts()
            BrowsePageType.RecentlyViewed -> Unit
        }
    }

    private fun fetchPopularProducts() {
        productRepository.getPopularProducts().request {
            _products.value = it
        }
    }
}