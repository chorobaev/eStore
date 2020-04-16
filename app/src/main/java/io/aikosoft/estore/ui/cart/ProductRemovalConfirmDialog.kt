package io.aikosoft.estore.ui.cart

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import io.aikosoft.estore.R
import io.aikosoft.estore.models.CartProduct
import kotlinx.android.synthetic.main.dialog_cart_product_removing.*

class ProductRemovalConfirmDialog(context: Context) : Dialog(context, R.style.DialogStyle) {

    init {
        setContentView(R.layout.dialog_cart_product_removing)
        setCancelable(true)
        setCanceledOnTouchOutside(true)

        iv_close.setOnClickListener {
            dismiss()
        }
    }

    fun setOnRemoveListener(block: (() -> Unit)?) {
        btn_remove.setOnClickListener {
            block?.invoke()
            dismiss()
        }
    }

    fun show(cartProduct: CartProduct) {
        Picasso.get().load(cartProduct.imageUrl).into(iv_product_image)
        show()
    }

    override fun show() {
        super.show()
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}