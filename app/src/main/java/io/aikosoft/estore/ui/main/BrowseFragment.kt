package io.aikosoft.estore.ui.main

import com.google.android.material.tabs.TabLayoutMediator
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment
import io.aikosoft.estore.models.BrowsePageType
import io.aikosoft.estore.ui.main.adapters.BrowseViewPagerAdapter
import io.aikosoft.estore.views.OnTabSelectedBoldListener
import kotlinx.android.synthetic.main.fragment_browse.*

class BrowseFragment : BaseFragment() {

    private lateinit var browseViewModel: BrowseViewModel
    private lateinit var browseViewPagerAdapter: BrowseViewPagerAdapter

    override val layoutRes: Int get() = R.layout.fragment_browse

    override fun onInitViewModel() {
        browseViewModel = getViewModel()
    }

    override fun onInitUI(firstInit: Boolean) {
        initViewPager()
        initTabLayout()
    }

    private fun initViewPager() {
        browseViewPagerAdapter = BrowseViewPagerAdapter(this)
        browse_view_pager.adapter = browseViewPagerAdapter
    }

    private fun initTabLayout() {
        browse_tab_layout.addOnTabSelectedListener(OnTabSelectedBoldListener())

        TabLayoutMediator(browse_tab_layout, browse_view_pager) { tab, position ->
            val tabNameRes = BrowsePageType.values()[position].stringRes
            tab.text = getString(tabNameRes)
        }.attach()
    }
}