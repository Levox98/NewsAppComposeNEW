package com.levox.newsapp.ui.app.component

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.levox.newsapp.data.models.Article
import com.levox.newsapp.data.models.Source
import com.levox.newsapp.ui.theme.NewsAppComposeTheme
import com.levox.newsapp.utils.Constants

@Composable
fun ArticleItem(
    article: Article,
    navController: NavHostController
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
                    navController.currentBackStackEntry?.savedStateHandle?.set(Constants.ARTICLE, article)
                    navController.navigate(Constants.DETAIL_SCREEN)
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
                        Text(text = "Author: ${article.author ?: "Unknown"} ")
                        Text(
                            text = "Published at: ${article.publishedAt}",
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = article.title!!,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = article.description!!,
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleItemPreview() {
    NewsAppComposeTheme {
        ArticleItem(
            Article(
                author = "John Doe", title = "Test Article", content = "This is a " +
                        "test article to test the tested functionality.",
                description = "This is a test description to test the description.",
                publishedAt = Constants.FROM, source = Source("1", "Me"), url = "",
                urlToImage = "https://media.wired.com/photos/62e9c5e1d7368105da057de3/191:100/w_1280,c_limit/BitRiver-Mining-Center-Rise-And-Fall-Of-Bitcoin-Mining-Business-1184520941.jpg"
            ), rememberNavController()
        )
    }
}