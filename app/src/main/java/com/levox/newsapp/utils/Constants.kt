package com.levox.newsapp.utils

import java.time.LocalDate

class Constants {
    companion object {
        const val BASE_URL = "https://newsapi.org"
        const val API_KEY = "d44b65951a5642dd9d9b699384536d16"
        val FROM = "${LocalDate.now().minusDays(30)}"

        const val MAIN_SCREEN = "main_screen"
        const val DETAIL_SCREEN = "detail_screen"
        const val ARTICLE = "article"
    }
}