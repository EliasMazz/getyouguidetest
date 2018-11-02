package br.com.getyourguide.review.domain.exception

class DefaultErrorBundle(private val mException: Exception?) : ErrorBundle {

    private val DEFAULT_ERROR_MSG = "Unknown error"

    override val exception: Exception?
        get() = this.mException


    override val errorMessage: String?
        get() = this.mException?.message ?: DEFAULT_ERROR_MSG

}