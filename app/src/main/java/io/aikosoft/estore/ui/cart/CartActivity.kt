package io.aikosoft.estore.ui.cart

import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseActivity
import io.aikosoft.estore.views.CheckoutSuccessSnackbar
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

    override fun onObserveViewModel() {
        cartViewModel.checkoutSuccess.observe(this, Observer {
            val content = findViewById<View>(android.R.id.content)
            CheckoutSuccessSnackbar.make(content, Snackbar.LENGTH_LONG)?.show()

            Thread {
                Thread.sleep(3000)
                runOnUiThread {
                    finish()
                }
            }.start()
        })
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
