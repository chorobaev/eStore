package io.aikosoft.estore.ui.detail

import androidx.lifecycle.Observer
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment
import io.aikosoft.estore.models.Product
import io.aikosoft.estore.models.Store
import io.aikosoft.estore.ui.detail.adapters.ImageViewPagerAdapter
import kotlinx.android.synthetic.main.content_buy.*
import kotlinx.android.synthetic.main.content_details_overview.*
import kotlinx.android.synthetic.main.item_store.*

class OverviewFragment : BaseFragment() {

    private lateinit var detailViewModel: DetailsViewModel
    private lateinit var imageViewPagerAdapter: ImageViewPagerAdapter

    override val layoutRes: Int get() = R.layout.fragment_overview

    override fun onInitViewModel() {
        detailViewModel = getViewModel()
    }

    override fun onInitUI(firstInit: Boolean) {
        initImageViewPager()
    }

    private fun initImageViewPager() {
        imageViewPagerAdapter = ImageViewPagerAdapter(this, listOf("a", "b"))
        images_view_pager.adapter = imageViewPagerAdapter
    }

    override fun onObserveViewModel() {
        detailViewModel.product.observe(this, Observer {
            it?.let { onProductReceived(it) }
        })

        detailViewModel.storeReviews.observe(this, Observer {
            store_reviews.setReviews(it)
        })

        detailViewModel.store.observe(this, Observer {
            it?.let { onStoreReceived(it) }
        })
    }

    private fun onProductReceived(product: Product) {
        detailViewModel.fetchAdditionalData(product.id)

        tv_image_count.text = product.imageUrls.size.toString()
        tv_description.text = product.description
        rating_bar.rating = product.rating.toFloat()
        tv_rated_count.text = getString(R.string.breck_int_breck, product.ratedCount)
        tv_price.text = getString(R.string.float_currency, product.price)
    }

    private fun onStoreReceived(store: Store) {
        tv_store_name.text = store.name
        tv_store_rating_value.text = getString(R.string.rating_value, store.rating)
        tv_store_rated_count.text = getString(R.string.breck_int_breck, store.ratedCount)
        store_rating_bar.rating = store.rating
    }
}