package br.com.getyourguide.review.presenter

import br.com.getyourguide.review.view.LoadDataView


abstract class LoadDataBasePresenter<T : LoadDataView> : BasePresenter<T>() {


    protected open fun showViewLoading() {
        this.view?.showLoading()
    }

    protected open fun hideViewLoading() {
        this.view?.hideLoading()
    }

    override fun parseError(e: Throwable) {
        super.parseError(e)
        hideViewLoading()
    }
}