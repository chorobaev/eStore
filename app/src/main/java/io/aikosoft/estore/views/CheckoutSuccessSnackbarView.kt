package io.aikosoft.estore.views

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.ContentViewCallback
import io.aikosoft.estore.R
import kotlinx.android.synthetic.main.view_checkout_success.view.*

class CheckoutSuccessSnackbarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr), ContentViewCallback {

    init {
        View.inflate(context, R.layout.view_checkout_success, this)
        clipToPadding = false
    }

    override fun animateContentOut(delay: Int, duration: Int) {
        val scaleX = ObjectAnimator.ofFloat(iv_app_icon, View.SCALE_X, 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(iv_app_icon, View.SCALE_Y, 0f, 1f)
        val animatorSet = AnimatorSet().apply {
            interpolator = OvershootInterpolator()
            setDuration(500)
            playTogether(scaleX, scaleY)
        }
        animatorSet.start()
    }

    override fun animateContentIn(delay: Int, duration: Int) {
    }
}