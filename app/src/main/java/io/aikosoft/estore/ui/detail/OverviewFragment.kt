package io.aikosoft.estore.ui.detail

import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment
import io.aikosoft.estore.ui.detail.adapters.ImageViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_overview.*

class OverviewFragment : BaseFragment() {

    private lateinit var imageViewPagerAdapter: ImageViewPagerAdapter

    override val layoutRes: Int get() = R.layout.fragment_overview

    override fun onInitUI(firstInit: Boolean) {
        initImageViewPager()
    }

    private fun initImageViewPager() {
        imageViewPagerAdapter = ImageViewPagerAdapter(this, listOf("a", "b"))
        images_view_pager.adapter = imageViewPagerAdapter
    }
}