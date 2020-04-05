package io.aikosoft.estore.utils

import android.view.View

fun Boolean?.toViesibility(): Int {
    return if (this == true) View.VISIBLE else View.GONE
}