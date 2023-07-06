package com.levox.api

import android.util.Log
import com.levox.api.entity.NewsResponseApiEntity
import com.levox.common.utils.AppError
import com.levox.common.utils.Either
import retrofit2.Response

abstract class BaseApi {
    suspend fun <T> doRequest(name: String = "", request: suspend () -> Either<T>): Either<T> {
        return try {
            request()
        } catch (e: Exception) {
            Log.d("API_ERROR", "$name ${e.message}")
            Either.error(AppError.Unknown(e.message ?: ""))
        }
    }

    suspend fun <E, R> doRequest(
        tag: String = "",
        request: suspend () -> Response<R>,
        mapper: suspend (R?) -> E?,
    ): Either<E> {
        return try {
            val response = request()
            if (response.isSuccessful) {
                val result = response.body() as NewsResponseApiEntity
                if (result.status == "ok") {
                    Either.success(mapper(response.body()))
                } else {
                    Either.error(AppError.Unknown(result.message ?: "Unknown error"))
                }
            } else {
                Either.error(AppError.Network())
            }
        } catch (e: Exception) {
            Log.e("API_ERROR", "${e.message}")
            Either.error(AppError.Unknown(e.message ?: ""))
        }
    }
}