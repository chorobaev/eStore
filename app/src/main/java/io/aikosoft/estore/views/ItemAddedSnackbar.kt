package io.aikosoft.estore.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import io.aikosoft.estore.R
import io.aikosoft.estore.utils.MyLogger
import io.aikosoft.estore.utils.findSuitableParent
import kotlinx.android.synthetic.main.view_item_added_to_cart.view.*

class ItemAddedSnackbar(
    parent: ViewGroup,
    content: ItemAddedSnackbarView
) : BaseTransientBottomBar<ItemAddedSnackbar>(parent, content, content) {

    init {
        view.setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.transparent))
        view.setPadding(0, 0, 0, 0)
    }

    companion object : MyLogger {

        fun make(
            view: View,
            imageUrl: String,
            saved: Double,
            duration: Int
        ): ItemAddedSnackbar? {

            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )

            try {
                val customView = LayoutInflater.from(view.context).inflate(
                    R.layout.view_item_added_snackbar,
                    parent,
                    false
                ) as ItemAddedSnackbarView

                // TODO: add image showing using Picasso
                customView.tv_you_saved.text =
                    customView.context.getString(R.string.you_saved_double_currency, saved)

                return ItemAddedSnackbar(
                    parent,
                    customView
                ).setDuration(duration)
            } catch (e: Exception) {
                log(e.message.toString())
            }

            return null
        }

    }
}