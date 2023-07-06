package com.levox.newsapp.ui.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.levox.newsapp.data.models.Article
import com.levox.newsapp.ui.app.component.ArticleImage

@Composable
fun DetailScreen(
    article: Article
) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .fillMaxWidth()
            ) {
                ArticleImage(imageUrl = article.urlToImage, 200, 1f)
                Text(text = "Author: ${article.author ?: "Unknown"}")
                Text(
                    text = "Published at: ${article.publishedAt}",
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = article.title ?: "",
                    fontWeight = FontWeight.Bold,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = article.content ?: "No content",
                    modifier = Modifier
                        .padding(5.dp)
                )
            }
        }
    }
}