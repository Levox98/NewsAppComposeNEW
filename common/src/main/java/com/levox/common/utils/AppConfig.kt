package com.levox.common.utils

import java.time.LocalDate

object AppConfig {
    object Constants {
        val FROM = "${LocalDate.now().minusDays(30)}"
        const val ARTICLE = "article"
    }

    object Api {
        const val BASE_URL = "https://newsapi.org"
        const val API_KEY = "d44b65951a5642dd9d9b699384536d16"
    }
}