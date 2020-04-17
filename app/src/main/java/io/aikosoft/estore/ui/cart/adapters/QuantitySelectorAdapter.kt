package io.aikosoft.estore.ui.cart.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.aikosoft.estore.R
import kotlinx.android.synthetic.main.item_number.view.*

typealias OnQuantitySelectListener = (quantity: Int) -> Unit

class QuantitySelectorAdapter(
    private val maxQuantity: Int = 50
) : RecyclerView.Adapter<QuantitySelectorAdapter.QuantityViewHolder>() {

    private var onQuantitySelectListener: OnQuantitySelectListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuantityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_number, parent, false)

        return QuantityViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuantityViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return maxQuantity + 1
    }

    fun setOnQuantitySelectListener(listener: OnQuantitySelectListener) {
        this.onQuantitySelectListener = listener
    }

    inner class QuantityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            itemView.tv_number.text = position.toString()

            itemView.setOnClickListener {
                onQuantitySelectListener?.invoke(adapterPosition)
            }
        }
    }
}