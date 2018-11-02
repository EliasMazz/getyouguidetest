package br.com.getyourguide.review.domain.interactor.usecases

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCase {

    private val mDisposables: CompositeDisposable = CompositeDisposable()

    fun dispose() {
        if (!mDisposables.isDisposed) {
            mDisposables.dispose()
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        checkNotNull(disposable)
        checkNotNull(mDisposables)
        mDisposables.add(disposable)
    }

}