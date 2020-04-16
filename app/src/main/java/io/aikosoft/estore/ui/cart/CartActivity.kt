package io.aikosoft.estore.ui.cart

import android.view.MenuItem
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseActivity
import kotlinx.android.synthetic.main.activity_details.*

class CartActivity : BaseActivity() {

    private lateinit var cartViewModel: CartViewModel

    override val layoutRes: Int get() = R.layout.activity_cart

    override fun onInitViewModel() {
        cartViewModel = getViewModel()
    }

    override fun onInitUI(firstInit: Boolean) {
        initAppBar()
    }

    private fun initAppBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setHomeAsUpIndicator(R.drawable.ic_close_light)
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
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
}
