package com.levox.ui_main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.levox.domain.entity.Article

class SharedViewModel : ViewModel() {
    var article by mutableStateOf<Article?>(null)
        private set

    fun setCurrentArticle(newArticle: Article) {
        article = newArticle
    }

    fun clearCurrentArticle() {
        article = null
    }
}