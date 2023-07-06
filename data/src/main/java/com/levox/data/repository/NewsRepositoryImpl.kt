package com.levox.data.repository

import com.levox.api.NewsApi
import com.levox.common.utils.AppError
import com.levox.common.utils.Either
import com.levox.data.entity.toDomain
import com.levox.domain.entity.Article
import com.levox.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getAllNews(query: String): Either<List<Article>> {
        val result = newsApi.getEverything(query)

        return when {
            result.isError() -> Either.error(result.error!!)
            result.isSuccess() && result.data != null -> {
                Either.success(result.data!!.toDomain())
            }
            else -> Either.error(AppError.Unknown())
        }
    }
}