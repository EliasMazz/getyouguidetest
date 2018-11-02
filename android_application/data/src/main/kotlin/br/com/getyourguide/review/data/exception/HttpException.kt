package br.com.getyourguide.review.data.exception

import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.Serializable


abstract class HttpException
constructor(private val retrofit: Retrofit,
            private val responseBody: ResponseBody?) : Exception() {

    private val responseError: ResponseError?

    init {
        responseError = getErrorBody()
    }

    private fun getErrorBody(): ResponseError? {
        if (responseBody == null) return null
        return generateConverter().convert(responseBody)
    }

    private fun generateConverter(): Converter<ResponseBody, ResponseError> {
        return retrofit.responseBodyConverter<ResponseError>(ResponseError::class.java, arrayOfNulls<Annotation>(0))
    }

    fun message(): String {
        if (responseError == null) return ""
        return responseError.message
    }

    fun fieldErrors(): Collection<FieldError>? {
        return responseError?.errors
    }

    internal data class ResponseError(@SerializedName("message") val message: String,
                                      @SerializedName("errors") val errors: Collection<FieldError>) : Serializable

    data class FieldError(@SerializedName("message") val message: String,
                          @SerializedName("field") val fieldName: String)

}