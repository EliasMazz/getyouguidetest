package br.com.getyourguide.review.data.exception

import okhttp3.ResponseBody
import retrofit2.Retrofit

class PasswordRequestException(retrofit: Retrofit, responseBody: ResponseBody?) : HttpException(retrofit, responseBody)
