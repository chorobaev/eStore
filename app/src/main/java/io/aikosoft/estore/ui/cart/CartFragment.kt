package io.aikosoft.estore.ui.cart

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment
import io.aikosoft.estore.models.CartProducts
import io.aikosoft.estore.ui.cart.adapters.CartProductAdapter
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : BaseFragment() {

    private val cartProductAdapter =
        CartProductAdapter(R.layout.content_cart_header, R.layout.content_cart_footer)

    private lateinit var cartViewModel: CartViewModel

    override val layoutRes: Int get() = R.layout.fragment_cart

    override fun onInitViewModel() {
        cartViewModel = getViewModel()
        cartViewModel.fetchData()
    }

    override fun onInitUI(firstInit: Boolean) {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        cart_recycler_view.run {
            layoutManager = LinearLayoutManager(baseActivity)
            setHasFixedSize(true)
            adapter = cartProductAdapter
        }
    }

    override fun onObserveViewModel() {
        cartViewModel.cartProducts.observe(this, Observer {
            it?.let { onCartProductsReceived(it) }
        })
    }

    private fun onCartProductsReceived(cartProducts: CartProducts) {
        cartProductAdapter.addCartProducts(cartProducts)
    }
}