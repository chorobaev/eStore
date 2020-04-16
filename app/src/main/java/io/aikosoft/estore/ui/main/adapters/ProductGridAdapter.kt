package io.aikosoft.estore.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.aikosoft.estore.R
import io.aikosoft.estore.models.Product
import io.aikosoft.estore.utils.dpToPx
import io.aikosoft.estore.utils.toVisibility
import kotlinx.android.synthetic.main.item_browse_product.view.*

private typealias OnProductClickListener = ((Product) -> Unit)

class ProductGridAdapter : RecyclerView.Adapter<ProductGridAdapter.ViewHolder>() {

    private var onProductClickListener: OnProductClickListener? = null
    private var products = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_browse_product, parent, false)

        val marginsInPx = dpToPx(parent.context, 8F)
        val screenWidth = parent.resources.displayMetrics.widthPixels.toFloat()
        val imageHeight = (screenWidth / 2 - marginsInPx)

        view.iv_image.run {
            layoutParams = layoutParams.apply { height = imageHeight.toInt() }
        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    fun addProducts(products: List<Product>) {
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    fun setOnProductsClickListener(onProductClickListener: OnProductClickListener?) {
        this.onProductClickListener = onProductClickListener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(product: Product) {
            with(itemView) {
                tv_price.text = context.getString(R.string.float_currency, product.price)
                tv_info.text = product.sellInfo

                if (product.discount == 0) {
                    tv_discount.visibility = GONE
                } else {
                    tv_discount.text = context.getString(R.string.int_percent, product.discount)
                }

                tv_almost_gone.visibility = product.isAlmostGone.toVisibility()

                if (product.imageUrls.isNotEmpty()) {
                    Picasso.get().load(product.imageUrls.first()).into(iv_image)
                }
            }

            itemView.setOnClickListener {
                try {
                    onProductClickListener?.invoke(products[adapterPosition])
                } catch (ignored: IndexOutOfBoundsException) {

                }
            }
        }
    }
}