package io.aikosoft.estore.models

import androidx.annotation.StringRes
import io.aikosoft.estore.R

enum class BrowsePageType(
    @StringRes val stringRes: Int
) {
    Popular(R.string.browse_type_popular),
    RecentlyViewed(R.string.browse_type_recently_viewed);
}