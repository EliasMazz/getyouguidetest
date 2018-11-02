package br.com.getyourguide.review.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.getyourguide.review.view.LoadDataView
import kotlinx.android.synthetic.main.fragment_review.*
import kotlinx.android.synthetic.main.layout_progressbar.*
import org.jetbrains.anko.toast
import timber.log.Timber

abstract class BaseFragment : Fragment(), LoadDataView {

    abstract val fragmentLayout: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragmentLayout, container, false)
    }

    /**
     * Shows a [android.widget.Toast] message.
     *
     * @param message An string representing a message to be shown.
     */
    protected fun showToastMessage(message: String) {
        context?.toast(message)
    }

    protected fun showToastMessage(messageResId: Int) {
        context?.toast(messageResId)
    }

    override fun showLoading() {
        Timber.d("Show loading")
        checkNotNull(progressbar_all)
        checkNotNull(linearlayout_container)
        progressbar_all.visibility = View.VISIBLE
        hideMainContainer()
    }

    override fun hideLoading() {
        Timber.d("Hide loading")
        progressbar_all.visibility = View.GONE
        showMainContainer()
    }

    protected fun showMainContainer() {
        linearlayout_container?.visibility = View.VISIBLE
    }

    protected fun hideMainContainer() {
        linearlayout_container?.visibility = View.GONE
    }

    override fun showError(message: String) {
        showToastMessage(message)
    }

    override fun context(): Context? {
        return activity
    }

}