package br.com.getyourguide.review.data.exception
import okhttp3.ResponseBody
import retrofit2.Retrofit

class NotFoundHttpException(retrofit: Retrofit, responseBody: ResponseBody?) : HttpException(retrofit, responseBody)
