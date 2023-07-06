package com.levox.api

import com.levox.api.entity.ArticleApiEntity
import com.levox.api.service.NewsApiService
import com.levox.common.utils.AppConfig
import com.levox.common.utils.Either
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsApi @Inject constructor(
    private val service: NewsApiService
) : BaseApi() {
    suspend fun getEverything(
        query: String,
        fromTime: String = AppConfig.Constants.FROM,
        apiKey: String = AppConfig.Api.API_KEY
    ): Either<List<ArticleApiEntity>> = doRequest(
        tag = "getEverything",
        request = {
            service.searchNews(
                searchQuery = query,
                fromTime = fromTime,
                apiKey = apiKey
            )
        },
        mapper = { newsResponseApiEntity ->
            newsResponseApiEntity?.articles
        }
    )

}