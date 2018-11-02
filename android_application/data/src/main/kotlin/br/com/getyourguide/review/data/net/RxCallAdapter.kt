package br.com.getyourguide.review.data.net

import br.com.getyourguide.review.data.exception.*
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type
import java.net.ConnectException

class RxCallAdapter<T>(
        private val retrofit: Retrofit,
        private val wrapped: CallAdapter<*, *>?,
        private val ramType: Class<*>) : CallAdapter<T, Any> {

    override fun responseType(): Type? {
        return wrapped?.responseType()
    }

    @Suppress("UNCHECKED_CAST")
    override fun adapt(call: Call<T>): Any {
        if (ramType == Completable::class) {
            return (wrapped as CallAdapter<T, Completable>).adapt(call)
                    .onErrorResumeNext({ throwable ->
                        Completable.error(asException(throwable))
                    })
        }
        return (wrapped as CallAdapter<T, Observable<Any>>)
                .adapt(call)
                .onErrorResumeNext({ t: Throwable -> Observable.error(asException(t)) })
    }

    private fun asException(throwable: Throwable): Exception {
        when (throwable) {
            is HttpException -> when (throwable.code()) {
                403, 401 -> {
                    return InvalidCredentialsException()
                }
                400 -> {
                    return PasswordRequestException(retrofit, throwable.response().errorBody())
                }
                422 -> {
                    return BadRequestException(retrofit, throwable.response().errorBody())
                }
                404 -> {
                    return NotFoundHttpException(retrofit, throwable.response().errorBody())
                }
                500, 504 -> {
                    return InternalServerErrorException()
                }
            }
            is ConnectException -> return InternalServerErrorException()
            is IOException -> return NetworkConnectionException()
        }
        return UnknownException()
    }
}