package br.com.getyourguide.review.presenter

import br.com.getyourguide.review.data.exception.ErrorMessageFactory
import br.com.getyourguide.review.domain.exception.DefaultErrorBundle
import br.com.getyourguide.review.domain.exception.ErrorBundle
import br.com.getyourguide.review.view.LoadDataView
import timber.log.Timber

abstract class BasePresenter<T: LoadDataView> : Presenter {

    protected var view: T? = null

    fun view(view: T) {
        this.view = view
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        this.view = null
    }

    protected open fun showErrorMessage(errorBundle: ErrorBundle) {
        val errorMessage = ErrorMessageFactory.create(view?.context()!!, errorBundle.exception)
        view?.showError(errorMessage)
    }


    protected open fun parseError(e: Throwable) {
        Timber.e(e)
        showErrorMessage(DefaultErrorBundle(e as Exception))
    }

}