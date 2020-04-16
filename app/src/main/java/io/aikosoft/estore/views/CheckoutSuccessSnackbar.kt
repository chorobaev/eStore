package io.aikosoft.estore.views

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import io.aikosoft.estore.utils.MyLogger
import io.aikosoft.estore.utils.findSuitableParent

class CheckoutSuccessSnackbar(
    parent: ViewGroup,
    content: CheckoutSuccessSnackbarView
) : BaseTransientBottomBar<CheckoutSuccessSnackbar>(parent, content, content) {

    init {
        view.setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.transparent))
        view.setPadding(0, 0, 0, 0)
    }

    companion object : MyLogger {

        fun make(view: View, duration: Int): CheckoutSuccessSnackbar? {

            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )

            try {
                val customView = CheckoutSuccessSnackbarView(view.context)

                return CheckoutSuccessSnackbar(parent, customView).setDuration(duration)
            } catch (e: Exception) {
                log(e.message.toString())
            }

            return null
        }

    }
}