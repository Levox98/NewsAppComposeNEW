package com.levox.newsapp.data.network

import com.levox.newsapp.data.models.NewsResponse
import com.levox.newsapp.utils.Constants.Companion.API_KEY
import com.levox.newsapp.utils.Constants.Companion.BASE_URL
import com.levox.newsapp.utils.Constants.Companion.FROM
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface NewsApiService {

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        searchQuery: String,
        @Query("from")
        fromTime: String = FROM,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : NewsResponse
}

object NewsApi {
    val retrofitService: NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}