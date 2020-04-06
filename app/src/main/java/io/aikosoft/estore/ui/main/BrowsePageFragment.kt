package io.aikosoft.estore.ui.main

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment
import io.aikosoft.estore.models.BrowsePageType
import io.aikosoft.estore.models.Product
import io.aikosoft.estore.ui.main.adapters.ProductGridAdapter
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
        // TODO: implement detail activity starting
        log(product.toString())
    }

    override fun onObserveViewModel() {
        browseViewModel.products.observe(this, Observer {
            it?.let {
                productGridAdapter.addProducts(it)
            }
        })

        browseViewModel.fetchProducts(BrowsePageType.Popular)
    }

    companion object {
        const val BROWSE_PAGE_TYPE = "io.aikosoft.estore.browse_page_type"
        const val COLUMN_COUNT = 2
    }
}