package io.aikosoft.estore.ui.detail.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.aikosoft.estore.ui.detail.ImageFragment

class ImageViewPagerAdapter(
    fragment: Fragment,
    private val imageUrls: List<String>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ImageFragment()
        fragment.arguments = Bundle().apply {
            putString(ImageFragment.IMAGE_URL, imageUrls[position])
        }
        return fragment
    }

}