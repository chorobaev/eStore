package io.aikosoft.estore.ui.cart

import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment

class OrderSummaryFragment : BaseFragment() {

    private lateinit var cartViewModel: CartViewModel

    override val layoutRes: Int get() = R.layout.fragment_order_summary

    override fun onInitViewModel() {
        cartViewModel = getViewModel()
    }
}