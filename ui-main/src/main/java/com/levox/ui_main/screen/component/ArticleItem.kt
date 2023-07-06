package com.levox.ui_main.screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.levox.base_ui.component.ArticleImage
import com.levox.domain.entity.Article
import com.levox.locale.R

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (Article) -> Unit
) {
    Surface(
        color = MaterialTheme.colors.secondary,
        elevation = 10.dp,
        modifier = modifier
            .padding(bottom = 16.dp)
            .clickable {
                onClick(article)
            },
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        ) {
            ArticleImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                imageUrl = article.urlToImage
            )
            Text(text = stringResource(id = R.string.author, article.author ?: "-"))
            Text(
                text = stringResource(id = R.string.publish_date, article.publishedAt ?: "-"),
                fontStyle = FontStyle.Italic
            )
            Text(
                text = article.title ?: stringResource(id = R.string.title_not_found),
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = article.description ?: stringResource(id = R.string.empty_description),
                modifier = Modifier
                    .padding(5.dp)
            )
        }
    }
}
