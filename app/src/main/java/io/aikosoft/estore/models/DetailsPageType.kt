package io.aikosoft.estore.models

import androidx.annotation.StringRes
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseFragment
import io.aikosoft.estore.ui.detail.OverviewFragment
import io.aikosoft.estore.ui.detail.RelatedFragment

enum class DetailsPageType(
    @StringRes val stringRes: Int
) {
    Overview(R.string.details_tab_overview),
    Related(R.string.details_tab_related);

    fun getRelatedFragment(): BaseFragment {
        return when (this) {
            Overview -> OverviewFragment()
            Related -> RelatedFragment()
        }
    }
}