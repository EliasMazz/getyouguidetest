package br.com.getyourguide.review.view

import android.content.Context

/**
 * Interface representing a View that will use to load data.
 */
interface LoadDataView {
    /**
     * Show a view with a progress bar indicating a loading process.
     */
    fun showLoading()

    /**
     * Hide a loading view.
     */
    fun hideLoading()

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    fun showError(message: String)

    /**
     * Get a [Context].
     */
    fun context(): Context?
}