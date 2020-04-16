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

class ProductGridAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onProductClickListener: OnProductClickListener? = null
    private var products = ArrayList<Product>()

    var loading: Boolean = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return if (loading) LOADING else PRODUCT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PRODUCT -> ViewHolder(getView(parent, viewType))
            LOADING -> LoadingViewHolder(getView(parent, viewType))
            else -> throw IllegalArgumentException("No such view type $viewType")
        }
    }

    private fun getView(parent: ViewGroup, viewType: Int): View {
        var layoutId = R.layout.item_browse_product
        var imageId = R.id.iv_image

        if (viewType == LOADING) {
            layoutId = R.layout.item_loading_browse_product
            imageId = R.id.shimmer_image
        }

        val view = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)

        val marginsInPx = dpToPx(parent.context, 8F)
        val screenWidth = parent.resources.displayMetrics.widthPixels.toFloat()
        val imageHeight = (screenWidth / 2 - marginsInPx)

        val image = view.findViewById<View>(imageId)
        image.run {
            layoutParams = layoutParams.apply { height = imageHeight.toInt() }
        }

        return view
    }

    override fun getItemCount(): Int {
        return if (loading) LOADING_ITEMS else products.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(products[position])
        }
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

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private const val LOADING_ITEMS = 8
        private const val LOADING = 1
        private const val PRODUCT = 0
    }
}