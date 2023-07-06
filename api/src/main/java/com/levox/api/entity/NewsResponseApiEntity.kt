package com.levox.api.entity

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseApiEntity(
    val articles: MutableList<ArticleApiEntity>,
    val status: String,
    val totalResults: Int,
    val message: String?
)