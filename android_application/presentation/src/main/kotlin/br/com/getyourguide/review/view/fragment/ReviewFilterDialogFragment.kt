package br.com.getyourguide.review.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.widget.RatingBar
import br.com.getyourguide.review.R

class ReviewFilterDialogFragment : DialogFragment() {

    interface Callback {
        fun onReviewFilterTip(ratingStar: Int)
    }

    private var reviewFilterTipCallBack: ReviewFilterDialogFragment.Callback? = null
    lateinit var ratingBar: RatingBar

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        reviewFilterTipCallBack = context as? Callback
    }

    override fun onDetach() {
        super.onDetach()
        reviewFilterTipCallBack = null
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val saveDialog = context?.let { ctx ->

            AlertDialog.Builder(ctx)
                    .setView(createView())
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Filter", { _, _ -> onReviewFilter() })
                    .setNeutralButton("Reset", { _, _ -> onReviewFilter(0) })
                    .create()
        }

        return saveDialog!!
    }

    private fun createView(): View {
        val layoutInflater: LayoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = layoutInflater.inflate(R.layout.dialog_fragment_filter, null)
        ratingBar = view.findViewById<RatingBar>(R.id.mr_review_rating_filter)


        return view
    }


    private fun onReviewFilter() {
        reviewFilterTipCallBack?.onReviewFilterTip(ratingBar.rating.toInt())
    }

    private fun onReviewFilter(rate: Int) {
        reviewFilterTipCallBack?.onReviewFilterTip(rate)
    }


    /*
    private fun onReviewFilter() {
        var listener = targetFragment as Callback
        listener.onReviewFilterTip(ratingBar.rating.toInt())
    }
    */


    //Life Cycle state restore
    companion object {
        val viewId = View.generateViewId()
    }
}

