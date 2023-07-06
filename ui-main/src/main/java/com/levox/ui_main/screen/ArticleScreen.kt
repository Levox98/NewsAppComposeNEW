package com.levox.ui_main.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.levox.base_ui.component.ArticleImage
import com.levox.domain.entity.Article
import com.levox.locale.R
import com.levox.ui_main.viewmodel.SharedViewModel


@Composable
fun ArticleScreen(
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController
) {
    val article = sharedViewModel.article

    BackHandler {
        sharedViewModel.clearCurrentArticle()
        navHostController.popBackStack()
    }

    if (article == null) {
        ArticleErrorScreen()
    } else {
        Article(
            article = article
        )
    }
}

@Composable
private fun Article(
    modifier: Modifier = Modifier,
    article: Article
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                ArticleImage(imageUrl = article.urlToImage)
                Text(text = stringResource(id = R.string.author, article.author ?: "-"))
                Text(
                    text = stringResource(
                        id = R.string.publish_date,
                        article.publishedAt ?: "-"
                    ),
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = article.title ?: stringResource(id = R.string.title_not_found),
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = article.content ?: stringResource(id = R.string.empty_content)
                )
            }
        }
    }
}

@Composable
private fun ArticleErrorScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.article_error))
    }
}