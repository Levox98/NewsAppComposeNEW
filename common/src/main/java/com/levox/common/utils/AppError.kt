package com.levox.common.utils

sealed class AppError(val code: Int, private val message: String = "") {

    class BadRequest(message: String = "") : AppError(badRequest, message)
    class Unauthorized(message: String = "") : AppError(unauthorized, message)
    class TooManyRequests(message: String = "") : AppError(tooManyRequests, message)
    class ServerError(message: String = "") : AppError(serverError, message)
    class Unknown(message: String = "") : AppError(0, message)
    class Network(message: String = "") : AppError(0, message)

    companion object {
        private const val badRequest = 400
        private const val unauthorized = 401
        private const val tooManyRequests = 429
        private const val serverError = 500

        fun error(code: Int, message: String = ""): AppError = when (code) {
            badRequest -> BadRequest(message)
            unauthorized -> Unauthorized(message)
            tooManyRequests -> TooManyRequests(message)
            serverError -> ServerError(message)
            else -> Unknown(message)
        }
    }

    fun message() = message.ifEmpty {
        when (code) {
            badRequest -> "The request was unacceptable, often due to a missing or misconfigured parameter."
            unauthorized -> "Your API key was missing from the request, or wasn't correct."
            tooManyRequests -> "You made too many requests within a window of time and have been rate limited. Back off for a while."
            serverError -> " Something went wrong on our side."
            else -> "Unknown error."
        }
    }
}