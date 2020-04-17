package io.aikosoft.estore.ui.main.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.aikosoft.estore.models.BrowsePageType
import io.aikosoft.estore.ui.main.BrowsePageFragment

class BrowseViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return BrowsePageType.values().size
    }

    override fun createFragment(position: Int): Fragment {

        val fragment = BrowsePageFragment()
        fragment.arguments = Bundle().apply {
            putSerializable(
                BrowsePageFragment.BROWSE_PAGE_TYPE,
                BrowsePageType.values()[position]
            )
        }

        return fragment
    }
}