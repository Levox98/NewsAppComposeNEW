package com.levox.api.entity

import kotlinx.serialization.Serializable

@Serializable
data class ArticleApiEntity(
    var id: Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceApiEntity?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
)