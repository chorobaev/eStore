package io.aikosoft.estore.ui.detail

import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment
import io.aikosoft.estore.models.Product
import io.aikosoft.estore.models.Store
import io.aikosoft.estore.ui.detail.adapters.ImageViewPagerAdapter
import io.aikosoft.estore.utils.toVisibility
import io.aikosoft.estore.views.ItemAddedSnackbar
import kotlinx.android.synthetic.main.content_details_overview.*
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlinx.android.synthetic.main.item_store.*
import kotlinx.android.synthetic.main.view_store_review.*

class OverviewFragment : BaseFragment() {

    private lateinit var detailViewModel: DetailsViewModel
    private lateinit var product: Product
    private lateinit var imageViewPagerAdapter: ImageViewPagerAdapter

    override val layoutRes: Int get() = R.layout.fragment_overview

    override fun onInitViewModel() {
        detailViewModel = getViewModel()
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

        detailViewModel.productAddingToCartSuccess.observe(this, Observer {
            val productImage =
                if (product.imageUrls.isNotEmpty()) product.imageUrls.first() else ""
            ItemAddedSnackbar.make(view!!, productImage, 14.5, Snackbar.LENGTH_LONG)?.show()
        })

        detailViewModel.loadingStoreReviews.observe(this, Observer {
            store_reviews.loading = it == true
        })

        detailViewModel.loadingStore.observe(this, Observer {
            loading_store_account.visibility = it.toVisibility()
        })
    }

    private fun onProductReceived(product: Product) {
        this.product = product
        initImageViewPager()

        tv_image_count.text = product.imageUrls.size.toString()
        tv_description.text = product.description
        rating_bar.rating = product.rating.toFloat()
        tv_rated_count.text = getString(R.string.breck_int_breck, product.ratedCount)
        tv_price.text = getString(R.string.float_currency, product.price)
    }

    private fun initImageViewPager() {
        val height = resources.displayMetrics.widthPixels
        images_view_pager.run {
            layoutParams = layoutParams.apply { this.height = height }
        }

        imageViewPagerAdapter = ImageViewPagerAdapter(this, product.imageUrls)
        images_view_pager.adapter = imageViewPagerAdapter
    }

    private fun onStoreReceived(store: Store) {
        lay_item_store.visibility = View.VISIBLE
        tv_store_name.text = store.name
        tv_store_rating_value.text = getString(R.string.rating_value, store.rating)
        tv_store_rated_count.text = getString(R.string.breck_int_breck, store.ratedCount)
        store_rating_bar.rating = store.rating

        Picasso.get().load(store.avatarUrl).into(civ_store_avatar.imageView)
    }

    override fun onSetOnClickListeners() {
        fab_like.setOnClickListener {
            // TODO: implement like logic
        }

        fab_share.setOnClickListener {
            // TODO: implement share product logic
        }

        tv_view_all_store_reviews.setOnClickListener {
            // TODO: implement view all product reviews
        }

        tv_view_store_rating.setOnClickListener {
            // TODO: implement view store reviews
        }

        btn_buy.setOnClickListener {
            addProductToCart()
        }
    }

    private fun addProductToCart() {
        detailViewModel.addProductToCart()
    }
}