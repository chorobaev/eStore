package io.aikosoft.estore.ui.cart

import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment
import io.aikosoft.estore.ui.cart.adapters.QuantitySelectorAdapter
import kotlinx.android.synthetic.main.fragment_quantity_selector.*

class QuantitySelectorFragment : BaseFragment() {

    private lateinit var cartViewModel: CartViewModel
    private lateinit var behavior: BottomSheetBehavior<FragmentContainerView>
    private val quantitySelectorAdapter = QuantitySelectorAdapter()

    override val layoutRes: Int get() = R.layout.fragment_quantity_selector

    override fun onInitViewModel() {
        cartViewModel = getViewModel()
    }

    override fun onInitUI(firstInit: Boolean) {
        behavior = BottomSheetBehavior.from(requireView().parent as FragmentContainerView)

        recycler_view_quantity.run {
            layoutManager = LinearLayoutManager(baseActivity)
            setHasFixedSize(true)
            adapter = quantitySelectorAdapter
        }

        quantitySelectorAdapter.setOnQuantitySelectListener {
            cartViewModel.changeProductQuantity(it)
            behavior.state = BottomSheetBehavior.STATE_HIDDEN
            recycler_view_quantity.scrollToPosition(0)
        }
    }
}