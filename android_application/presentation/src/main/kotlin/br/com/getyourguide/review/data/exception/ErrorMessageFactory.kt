package br.com.getyourguide.review.data.exception

import android.content.Context
import br.com.getyourguide.review.R

open class ErrorMessageFactory {

    companion object {

        /**
         * Creates a String representing an error message.
         *
         * @param context Context needed to retrieve string resources.
         * @param exception An exception used as a condition to retrieve the correct error message.
         * @return [String] an error message.
         */
        fun create(context: Context, exception: Exception?): String {
            return when (exception) {
                is NetworkConnectionException -> context.getString(R.string.exception_message_no_connection)
                is InvalidCredentialsException -> context.getString(R.string.exception_message_invalid_credentials)
                is NotFoundHttpException -> exception.message()
                is BadRequestException -> exception.message()
                is InternalServerErrorException -> context.getString(R.string.exception_message_internal_server_error)
                is UnknownException -> context.getString(R.string.exception_message_unknown)
                else -> context.getString(R.string.exception_message_generic)
            }
        }
    }
}