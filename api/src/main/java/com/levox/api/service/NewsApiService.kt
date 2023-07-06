package com.levox.api.service

import com.levox.api.entity.NewsResponseApiEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        searchQuery: String,
        @Query("from")
        fromTime: String,
        @Query("apiKey")
        apiKey: String
    ) : Response<NewsResponseApiEntity>
}