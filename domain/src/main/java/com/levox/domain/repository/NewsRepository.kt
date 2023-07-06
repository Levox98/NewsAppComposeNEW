package com.levox.domain.repository

import com.levox.common.utils.Either
import com.levox.domain.entity.Article

interface NewsRepository {
    suspend fun getAllNews(query: String): Either<List<Article>>
}