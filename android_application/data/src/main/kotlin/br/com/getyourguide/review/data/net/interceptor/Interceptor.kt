package br.com.getyourguide.review.data.net.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Interceptor
@Inject constructor() : Interceptor {

    //TODO HARDCODED to pass HTTPS request
    private val userAgent: String = "Test"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(putAuthorizationHeaderInRequest(chain.request()))
    }

    private fun putAuthorizationHeaderInRequest(request: Request): Request {
        val requestBuilder = request.newBuilder()
                .header("Content-Type", "application/json")
                .addHeader("User-Agent", userAgent)

        requestBuilder.method(request.method(), request.body())
        return requestBuilder.build()
    }
}
