package br.com.getyourguide.review.view.fragment

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.layout_toolbar.*

abstract class BaseToolbarFragment : BaseFragment() {

    abstract val actionBarTitleResource: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configActionBar()
    }

    private fun configActionBar() {
        changeActionBarTitle(actionBarTitleResource)

    }

    private fun changeActionBarTitle(stringRes: Int) {
        this.toolbar_all!!.title = context!!.resources?.getString(stringRes)
    }
}