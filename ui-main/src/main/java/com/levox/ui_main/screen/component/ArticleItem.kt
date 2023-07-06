package com.levox.ui_main.screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.levox.base_ui.component.ArticleImage
import com.levox.domain.entity.Article
import com.levox.locale.R

@Composable
fun ArticleItem(
    article: Article,
    onClick: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Card(
            backgroundColor = Color.LightGray,
            elevation = 10.dp,
            modifier = Modifier
                .clickable {
                    onClick(article)
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row {
                    ArticleImage(imageUrl = article.urlToImage, 100, .5f)
                    Column(
                        modifier = Modifier
                            .padding(start = 4.dp)
                    ) {
                        Text(text = stringResource(id = R.string.author, article.author ?: "-"))
                        Text(
                            text = stringResource(id = R.string.publish_date, article.publishedAt ?: "-"),
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = article.title ?: stringResource(id = R.string.title_not_found),
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = article.description ?: stringResource(id = R.string.empty_description),
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}