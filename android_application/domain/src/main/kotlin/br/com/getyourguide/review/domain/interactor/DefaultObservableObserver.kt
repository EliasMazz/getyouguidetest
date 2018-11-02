package br.com.getyourguide.review.domain.interactor

import io.reactivex.observers.DisposableObserver

abstract class DefaultObservableObserver<T> : DisposableObserver<T>()