package br.com.getyourguide.review.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import br.com.getyourguide.review.R
import br.com.getyourguide.review.model.ReviewModel
import br.com.getyourguide.review.model.SectionBody
import javax.inject.Inject

class ReviewListAdapter
@Inject constructor( context: Context) : BaseReviewListAdapter<ReviewModel>(context) {

    override val itemLayoutResource: Int
        get() = R.layout.item_review_list

    override val itemHeaderLayoutResource: Int
        get() = R.layout.header_item_review

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflateViewByViewType(parent, viewType)
        if (viewType == ITEM_VIEW_TYPE_SECTION_HEADER) return HeaderViewHolder(view)
        if (viewType == ITEM_VIEW_TYPE_LIST_HEADER) return ListHeaderViewHolder(view)
        return TransactionViewHolder(view)
    }

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, sectionBody: SectionBody) {
        val transactionViewHolder = holder as TransactionViewHolder
        val review = sectionBody.review

        transactionViewHolder.tvItemReviewTitle.visibility = View.VISIBLE
        transactionViewHolder.tvItemReviewMessage.visibility = View.VISIBLE

        if (review.title.isNullOrEmpty()) transactionViewHolder.tvItemReviewTitle.visibility = View.GONE else View.VISIBLE
        if (review.message.isNullOrEmpty()) transactionViewHolder.tvItemReviewMessage.visibility = View.GONE else View.VISIBLE

        transactionViewHolder.tvItemReviewMessage.text = review.message
        transactionViewHolder.tvItemReviewTitle.text = review.title
        transactionViewHolder.tvItemReviewAuthor.text = review.author
        transactionViewHolder.tvItemReviewRating.rating = review.rating!!.toFloat()


        //when (review.foreignLanguage) {
        //    false -> isInactive(transactionViewHolder, review)
       // }
    }

    /*
    private fun setColor(transactionViewHolder: TransactionViewHolder) {
        transactionViewHolder.tvItemReviewAuthor.setTextColor(context.resources.getColor(R.color.grey))
        transactionViewHolder.tvItemReviewTitle.setTextColor(context.resources.getColor(R.color.grey))
        transactionViewHolder.tvItemReviewMessage.setTextColor(context.resources.getColor(R.color.grey))
        transactionViewHolder.tvItemReviewRating.setTextColor(context.resources.getColor(R.color.grey))
    }

    private fun isInactive(transactionViewHolder: TransactionViewHolder, review: ReviewModel) {
        transactionViewHolder.tvItemReviewAuthor.setTextColor(context.resources.getColor(R.color.greyTransparent))
        transactionViewHolder.tvItemReviewTitle.setTextColor(context.resources.getColor(R.color.greyTransparent))
        transactionViewHolder.tvItemReviewMessage.setTextColor(context.resources.getColor(R.color.greyTransparent))
        transactionViewHolder.tvItemReviewRating.setTextColor(context.resources.getColor(R.color.greyTransparent))
    }
     */

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemReviewRating: RatingBar = itemView.findViewById(R.id.mr_review_rating)
        val tvItemReviewTitle: TextView = itemView.findViewById(R.id.tv_review_title)
        val tvItemReviewMessage: TextView = itemView.findViewById(R.id.tv_review_message)
        val tvItemReviewAuthor: TextView = itemView.findViewById(R.id.tv_review_author)
    }

}