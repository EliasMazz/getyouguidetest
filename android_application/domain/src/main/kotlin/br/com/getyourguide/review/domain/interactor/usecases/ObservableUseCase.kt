package br.com.getyourguide.review.domain.interactor.usecases

import br.com.getyourguide.review.domain.executor.PostExecutionThread
import br.com.getyourguide.review.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T, in R>(
        private val mThreadExecutor: ThreadExecutor,
        private val mPostExecutionThread: PostExecutionThread) : UseCase() {

    abstract fun buildUseCaseObservable(params: R): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: R) {
        checkNotNull(observer)
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

}