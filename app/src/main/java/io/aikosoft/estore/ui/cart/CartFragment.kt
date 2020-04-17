package io.aikosoft.estore.ui.cart

import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment
import io.aikosoft.estore.models.CartProducts
import io.aikosoft.estore.ui.cart.adapters.CartProductAdapter
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : BaseFragment() {

    private lateinit var cartViewModel: CartViewModel
    private lateinit var quantitySelectorBehavior: BottomSheetBehavior<FragmentContainerView>
    private lateinit var productRemovalConfirmDialog: ProductRemovalConfirmDialog
    private val cartProductAdapter =
        CartProductAdapter(R.layout.content_cart_header, R.layout.content_cart_footer)

    override val layoutRes: Int get() = R.layout.fragment_cart

    override fun onInitViewModel() {
        cartViewModel = getViewModel()
        cartViewModel.fetchData()
    }

    override fun onInitUI(firstInit: Boolean) {
        initQuantitySelectingSheetBehavior()
        initProductRemovalConfirmDialog()
        initRecyclerView()
    }

    private fun initQuantitySelectingSheetBehavior() {
        quantitySelectorBehavior = BottomSheetBehavior.from(fragment_container_quantity_selector)
        quantitySelectorBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun initProductRemovalConfirmDialog() {
        productRemovalConfirmDialog = ProductRemovalConfirmDialog(baseActivity)

        productRemovalConfirmDialog.setOnRemoveListener {
            cartViewModel.removeProduct()
        }
    }

    private fun initRecyclerView() {
        cart_recycler_view.run {
            layoutManager = LinearLayoutManager(baseActivity)
            setHasFixedSize(true)
            adapter = cartProductAdapter
        }

        cartProductAdapter.setOnProductQuantityChangeListener { cartProduct ->
            cartViewModel.selectedCartProduct = cartProduct
            quantitySelectorBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun onObserveViewModel() {
        cartViewModel.cartProducts.observe(this, Observer {
            it?.let { onCartProductsReceived(it) }
        })

        cartViewModel.shouldConfirmProductRemoval.observe(this, Observer {
            it?.let { productRemovalConfirmDialog.show(it) }
        })

        cartViewModel.loadingCartProducts.observe(this, Observer {
            cartProductAdapter.loading = it == true
        })
    }

    private fun onCartProductsReceived(cartProducts: CartProducts) {
        cartProductAdapter.updateCartProducts(cartProducts)
    }

    override fun onSetOnClickListeners() {
        btn_checkout.setOnClickListener {
            cartViewModel.checkout()
        }
    }
}