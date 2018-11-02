package br.com.getyourguide.review.data.exception


class NetworkConnectionException : Exception {
    constructor() : super()
    constructor(ex: Throwable?) : super(ex)
}