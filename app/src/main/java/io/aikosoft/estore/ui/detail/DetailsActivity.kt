package io.aikosoft.estore.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseActivity
import io.aikosoft.estore.models.DetailsPageType
import io.aikosoft.estore.models.Product
import io.aikosoft.estore.ui.cart.CartActivity
import io.aikosoft.estore.ui.detail.adapters.DetailsViewPagerAdapter
import io.aikosoft.estore.views.OnTabSelectedBoldListener
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : BaseActivity() {

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var product: Product
    private lateinit var detailsViewPagerAdapter: DetailsViewPagerAdapter

    override val layoutRes: Int get() = R.layout.activity_details

    override fun onCreate(savedInstanceState: Bundle?) {
        product = intent?.getSerializableExtra(PRODUCT) as? Product
            ?: throw IllegalArgumentException("Product is not provided!")

        super.onCreate(savedInstanceState)
    }

    override fun onInitViewModel() {
        detailsViewModel = getViewModel()
        detailsViewModel.setProduct(product)
    }

    override fun onInitUI(firstInit: Boolean) {
        initAppBar()
        initViewPager()
        initTabLayout()
    }

    private fun initAppBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initViewPager() {
        detailsViewPagerAdapter = DetailsViewPagerAdapter(this)
        details_view_pager.adapter = detailsViewPagerAdapter
    }

    private fun initTabLayout() {
        details_tab_layout.addOnTabSelectedListener(OnTabSelectedBoldListener())

        TabLayoutMediator(details_tab_layout, details_view_pager) { tab, position ->
            tab.text = getString(DetailsPageType.values()[position].stringRes)
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onObserveViewModel() {

    }

    override fun onSetOnClickListeners() {
        iv_cart.setOnClickListener {
            startCartActivity()
        }
    }

    private fun startCartActivity() {
        Intent(this, CartActivity::class.java).also {
            startActivity(it)
        }
    }

    companion object {
        const val PRODUCT = "io.aikosoft.estore.product_id"
    }
}
