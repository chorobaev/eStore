package io.aikosoft.estore.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import io.aikosoft.estore.R
import io.aikosoft.estore.models.StoreReview
import io.aikosoft.estore.utils.MyLogger
import io.aikosoft.estore.utils.toViesibility
import kotlinx.android.synthetic.main.item_store_review.view.*
import kotlinx.android.synthetic.main.view_store_review.view.*

class StoreReviewView(context: Context, attributeSet: AttributeSet? = null) :
    FrameLayout(context, attributeSet), MyLogger {

    var showFirst = 3
    private var itemViews = ArrayList<View>()

    init {
        LayoutInflater.from(context).inflate(R.layout.view_store_review, this)
    }

    fun setReviews(newReviews: List<StoreReview>?) {
        itemViews.forEach {
            store_review_content.removeView(it)
        }
        itemViews.clear()

        tv_no_store_reviews.visibility = newReviews.isNullOrEmpty().toViesibility()

        if (newReviews != null) {
            var index = 0
            while (index < newReviews.size.coerceAtMost(showFirst)) {
                getItemStoreReviewView(newReviews[index]).also { view ->
                    view.id = 1000 + index
                    itemViews.add(view)
                    store_review_content.addView(view)
                }
                index++
            }
        }
    }

    private fun getItemStoreReviewView(storeReview: StoreReview): View {
        log(storeReview.toString())
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_store_review, store_review_content, false)

        with(view) {
            tv_reviewer_name.text = storeReview.reviewer
            tv_review_message.text = storeReview.reviewMessage

            // TODO: implement N time ago logic
            tv_reviewed_date.text = "1 day ego"
            reviewer_rating_bar.rating = storeReview.rating.toFloat()
        }

        return view
    }
}