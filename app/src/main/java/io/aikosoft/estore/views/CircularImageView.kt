package io.aikosoft.estore.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import io.aikosoft.estore.R
import kotlinx.android.synthetic.main.view_circle_image_view.view.*

class CircularImageView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleType: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleType) {

    val imageView: ImageView
        get() = image_view

    init {
        View.inflate(context, R.layout.view_circle_image_view, this)
    }
}