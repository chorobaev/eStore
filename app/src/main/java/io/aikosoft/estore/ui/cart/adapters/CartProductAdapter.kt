package io.aikosoft.estore.ui.cart.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import io.aikosoft.estore.R
import io.aikosoft.estore.models.CartProduct
import io.aikosoft.estore.models.CartProducts
import io.aikosoft.estore.utils.MyLogger

class CartProductAdapter(
    @LayoutRes val headerLayoutRes: Int = -1,
    @LayoutRes val footerLayoutRes: Int = -1
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val cartProducts = ArrayList<CartProduct>()

    override fun getItemViewType(position: Int): Int {
        var type = PRODUCT

        if (headerLayoutRes != -1 && position == 0) {
            type = HEADER
        }

        if (footerLayoutRes != -1 && position == itemCount - 1) {
            type = FOOTER
        }

        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        return when (viewType) {
            PRODUCT -> CartProductViewHolder(
                inflater.inflate(R.layout.item_cart_product, parent, false),
                cartProducts
            )
            HEADER -> HeaderViewHolder(inflater.inflate(headerLayoutRes, parent, false))
            FOOTER -> FooterViewHolder(inflater.inflate(footerLayoutRes, parent, false))
            else -> throw IllegalArgumentException("No such viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CartProductViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        var size = cartProducts.size

        if (headerLayoutRes != -1) {
            size++
        }

        if (footerLayoutRes != -1) {
            size++
        }

        return size
    }

    fun addCartProducts(cartProducts: CartProducts) {
        this.cartProducts.run {
            clear()
            addAll(cartProducts)
        }
        notifyDataSetChanged()
    }

    class CartProductViewHolder(
        itemView: View,
        private val cartProducts: CartProducts
    ) : RecyclerView.ViewHolder(itemView), MyLogger {

        fun bind(position: Int) {
            //log("In view: ${cartProducts[position]}")
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private const val PRODUCT = 0
        private const val HEADER = 1
        private const val FOOTER = 2
    }
}