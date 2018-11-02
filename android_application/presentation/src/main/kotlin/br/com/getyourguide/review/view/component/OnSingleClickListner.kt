package br.com.getyourguide.review.view.component

import android.view.View

abstract class OnSingleClickListener : View.OnClickListener {

    private var mLastClickTime: Long = 0

    override fun onClick(v: View) {
        val lastClickTime = mLastClickTime
        val now = System.currentTimeMillis()
        mLastClickTime = now
        if (now - lastClickTime < MIN_DELAY_MS) {
        } else {
            onSingleClick(v)
        }
    }

    abstract fun onSingleClick(v: View)

    companion object {
        private val TAG = OnSingleClickListener::class.java.simpleName

        private const val MIN_DELAY_MS: Long = 500

        fun wrap(onClickListener: View.OnClickListener): View.OnClickListener {
            return object : OnSingleClickListener() {
                override fun onSingleClick(v: View) {
                    onClickListener.onClick(v)
                }
            }
        }
    }
}