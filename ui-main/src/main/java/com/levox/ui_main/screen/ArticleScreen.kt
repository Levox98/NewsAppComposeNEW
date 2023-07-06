package com.levox.ui_main.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.levox.base_ui.component.ArticleImage
import com.levox.locale.R
import com.levox.ui_main.viewmodel.ArticleScreenViewModel
import com.levox.ui_main.viewmodel.SharedViewModel


@Composable
fun ArticleScreen(
    viewModel: ArticleScreenViewModel,
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController
) {
    val article = sharedViewModel.article

    BackHandler {
        sharedViewModel.clearCurrentArticle()
        navHostController.popBackStack()
    }

    if (article == null) {
        Text(text = "Oops, someting went wrong")
    } else {
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = article.content ?: stringResource(id = R.string.empty_content),
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}