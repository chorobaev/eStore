package io.aikosoft.estore.ui.cart

import androidx.lifecycle.Observer
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment
import io.aikosoft.estore.models.CartProducts
import io.aikosoft.estore.utils.toVisibility
import kotlinx.android.synthetic.main.content_order_summary.*
import kotlinx.android.synthetic.main.fragment_order_summary.*

class OrderSummaryFragment : BaseFragment() {

    private lateinit var cartViewModel: CartViewModel

    override val layoutRes: Int get() = R.layout.fragment_order_summary

    override fun onInitViewModel() {
        cartViewModel = getViewModel()
    }

    override fun onObserveViewModel() {
        cartViewModel.cartProducts.observe(this, Observer {
            it?.let { onCartProductsReceived(it) }
        })

        cartViewModel.loadingCartProducts.observe(this, Observer {
            content_loading_order_summary.visibility = it.toVisibility()
            content_order_summary.visibility = (it != true).toVisibility()
        })
    }

    private fun onCartProductsReceived(cartProducts: CartProducts) {
        val totalPrice = cartProducts.sumByDouble { it.totalDiscountedPrice }
        val shippingPrice = cartProducts.sumByDouble { it.shippingPrice }

        tv_item_total_price.text = getString(R.string.float_currency, totalPrice)
        tv_shipping_price.text = getString(R.string.float_currency, shippingPrice)
        tv_order_total_price.text = getString(R.string.float_currency, totalPrice + shippingPrice)
    }
}