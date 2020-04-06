package io.aikosoft.estore.ui.detail.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.aikosoft.estore.models.DetailsPageType

class DetailsViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return DetailsPageType.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return DetailsPageType.values()[position].getRelatedFragment()
    }
}