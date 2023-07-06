package com.levox.data.entity

import com.levox.api.entity.ArticleApiEntity
import com.levox.domain.entity.Article

fun ArticleApiEntity.toDomain() =
    Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source?.toDomain(),
        title = title,
        url = url,
        urlToImage = urlToImage
    )

fun List<ArticleApiEntity>.toDomain() = this.map { it.toDomain() }