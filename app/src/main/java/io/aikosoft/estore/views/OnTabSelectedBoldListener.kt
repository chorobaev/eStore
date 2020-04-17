package io.aikosoft.estore.views

import android.view.View
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import com.google.android.material.tabs.TabLayout
import io.aikosoft.estore.R

class OnTabSelectedBoldListener : TabLayout.OnTabSelectedListener {

    override fun onTabReselected(tab: TabLayout.Tab) {}

    override fun onTabUnselected(tab: TabLayout.Tab) {
        val views = arrayListOf<View>()
        tab.view.findViewsWithText(views, tab.text, View.FIND_VIEWS_WITH_TEXT)
        views.forEach { view ->
            if (view is TextView) {
                TextViewCompat.setTextAppearance(view, R.style.TabTextAppearance)
            }
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        val views = arrayListOf<View>()
        tab.view.findViewsWithText(views, tab.text, View.FIND_VIEWS_WITH_TEXT)
        views.forEach { view ->
            if (view is TextView) {
                TextViewCompat.setTextAppearance(view, R.style.TabTextAppearance_Selected)
            }
        }
    }
}