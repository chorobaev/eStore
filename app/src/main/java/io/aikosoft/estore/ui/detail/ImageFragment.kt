package io.aikosoft.estore.ui.detail

import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment

class ImageFragment : BaseFragment() {

    override val layoutRes: Int get() = R.layout.fragment_image

    companion object {
        const val IMAGE_URL = "io.aikosoft.estore.image_url"
    }
}