package io.aikosoft.estore.ui.cart.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.aikosoft.estore.R
import io.aikosoft.estore.models.CartProduct
import io.aikosoft.estore.models.CartProducts
import io.aikosoft.estore.utils.MyLogger
import kotlinx.android.synthetic.main.item_cart_product.view.*

typealias OnProductQuantityChangeListener = (cartProduct: CartProduct) -> Unit

class CartProductAdapter(
    @LayoutRes val headerLayoutRes: Int = -1,
    @LayoutRes val footerLayoutRes: Int = -1
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var loading: Boolean = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var cartProducts: ArrayList<CartProduct>? = null
    private var onProductQuantityChangeListener: OnProductQuantityChangeListener? = null

    override fun getItemViewType(position: Int): Int {
        var type = if (loading) LOADING else PRODUCT

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
            LOADING -> LoadingViewHolder(
                inflater.inflate(R.layout.item_loading_cart_product, parent, false)
            )
            PRODUCT -> CartProductViewHolder(
                inflater.inflate(R.layout.item_cart_product, parent, false)
            )
            HEADER -> HeaderViewHolder(inflater.inflate(headerLayoutRes, parent, false))
            FOOTER -> FooterViewHolder(inflater.inflate(footerLayoutRes, parent, false))
            else -> throw IllegalArgumentException("No such viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CartProductViewHolder) {
            cartProducts?.let {
                holder.bind(it[getRelativeAdapterPosition(position)])
            }
        }
    }

    private fun getRelativeAdapterPosition(actualPosition: Int): Int {
        var position = actualPosition
        if (headerLayoutRes != -1) {
            position--
        }
        return position
    }

    override fun getItemCount(): Int {
        var size = cartProducts?.size ?: 0

        if (loading) {
            size = 1
        }

        if (headerLayoutRes != -1) {
            size++
        }

        if (footerLayoutRes != -1) {
            size++
        }

        return size
    }

    fun setOnProductQuantityChangeListener(listener: OnProductQuantityChangeListener) {
        this.onProductQuantityChangeListener = listener
    }

    fun updateCartProducts(cartProducts: CartProducts) {
        this.cartProducts = ArrayList(cartProducts)
        notifyDataSetChanged()
    }

    inner class CartProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        MyLogger {


        fun bind(product: CartProduct) {

            with(itemView) {
                tv_product_name.text = product.name
                tv_discounted_price.text =
                    context.getString(R.string.float_currency, product.totalDiscountedPrice)
                tv_actual_price.text =
                    context.getString(R.string.float_currency, product.totalActualPrice)
                tv_actual_price.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                tv_bulk_and_color.text =
                    context.getString(R.string.str_coma_str, product.bulk, product.color)
                tv_shipping_price.text =
                    context.getString(R.string.float_currency, product.shippingPrice)
                tv_extra_information.text = product.extra
                tv_quantity.text = product.quantity.toString()

                Picasso.get().load(product.imageUrl).into(iv_product_image)

                tv_quantity.setOnClickListener {
                    cartProducts?.let {
                        val position = getRelativeAdapterPosition(adapterPosition)
                        onProductQuantityChangeListener?.invoke(it[position])
                    }
                }
            }
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private const val PRODUCT = 0
        private const val HEADER = 1
        private const val FOOTER = 2
        private const val LOADING = 3
    }
}