package io.aikosoft.estore.ui.cart

import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment
import io.aikosoft.estore.models.PaymentMethod
import io.aikosoft.estore.models.ShippingAddress
import kotlinx.android.synthetic.main.content_shopping_payment.*
import kotlinx.android.synthetic.main.fragment_shipping_payment.*

class ShoppingPaymentFragment : BaseFragment() {

    private lateinit var cartViewModel: CartViewModel

    override val layoutRes: Int get() = R.layout.fragment_shipping_payment

    override fun onInitViewModel() {
        cartViewModel = getViewModel()
    }

    override fun onObserveViewModel() {
        cartViewModel.shippingAddress.observe(this, Observer {
            it?.let { onShippingAddressReceived(it) }
        })

        cartViewModel.paymentMethod.observe(this, Observer {
            it?.let { onPaymentMethodReceived(it) }
        })
    }

    private fun onShippingAddressReceived(shippingAddress: ShippingAddress) {
        section_shopping_payment.visibility = VISIBLE
        item_ship_to.visibility = VISIBLE

        tv_address.text = shippingAddress.firstAddressLine
    }

    private fun onPaymentMethodReceived(paymentMethod: PaymentMethod) {
        section_shopping_payment.visibility = VISIBLE
        item_payment.visibility = VISIBLE

        tv_payment_method.text = paymentMethod.name
    }
}