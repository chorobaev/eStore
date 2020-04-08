package io.aikosoft.estore.ui.detail

import com.squareup.picasso.Picasso
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment : BaseFragment() {

    private lateinit var imageUrl: String

    override val layoutRes: Int get() = R.layout.fragment_image

    override fun onInitUI(firstInit: Boolean) {
        imageUrl = arguments?.getString(IMAGE_URL, null)
            ?: throw IllegalArgumentException("Image url must not be null")

        Picasso.get().load(imageUrl).into(iv_image)
    }

    companion object {
        const val IMAGE_URL = "io.aikosoft.estore.image_url"
    }
}