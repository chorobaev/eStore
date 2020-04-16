package io.aikosoft.estore.ui.main

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment
import io.aikosoft.estore.models.BrowsePageType
import io.aikosoft.estore.models.Product
import io.aikosoft.estore.ui.detail.DetailsActivity
import io.aikosoft.estore.ui.main.adapters.ProductGridAdapter
import io.aikosoft.estore.utils.toVisibility
import kotlinx.android.synthetic.main.fragment_page_browse.*

class BrowsePageFragment : BaseFragment() {

    private lateinit var browseViewModel: BrowseViewModel
    private lateinit var pageType: BrowsePageType
    private val productGridAdapter = ProductGridAdapter()

    override val layoutRes: Int get() = R.layout.fragment_page_browse

    override fun onInitViewModel() {
        browseViewModel = getViewModel()
    }

    override fun onInitUI(firstInit: Boolean) {
        pageType = arguments?.getSerializable(BROWSE_PAGE_TYPE) as? BrowsePageType
            ?: throw IllegalArgumentException("Tab type is not provided")

        onInitRecyclerView()
    }

    private fun onInitRecyclerView() {
        recycler_view.layoutManager = GridLayoutManager(baseActivity, COLUMN_COUNT)
        recycler_view.adapter = productGridAdapter

        productGridAdapter.setOnProductsClickListener(this::startDetailActivity)
    }

    private fun startDetailActivity(product: Product) {
        Intent(baseActivity, DetailsActivity::class.java).also {
            it.putExtra(DetailsActivity.PRODUCT, product)
            startActivity(it)
        }
    }

    override fun onObserveViewModel() {
        browseViewModel.products.observe(this, Observer {
            it?.let {
                productGridAdapter.addProducts(it)
            }
        })

        browseViewModel.fetchProducts(BrowsePageType.Popular)

        browseViewModel.productsLoading.observe(this, Observer {
            touch_disabling.visibility = it.toVisibility()
            productGridAdapter.loading = it == true
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        log("destroyed")
    }

    companion object {
        const val BROWSE_PAGE_TYPE = "io.aikosoft.estore.browse_page_type"
        const val COLUMN_COUNT = 2
    }
}