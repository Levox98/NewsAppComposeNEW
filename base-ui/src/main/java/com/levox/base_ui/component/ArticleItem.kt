package com.levox.base_ui.component

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
import androidx.compose.ui.unit.dp

@Composable
fun ArticleItem(
    imageUrl: String?,
    author: String?,
    publishDate: String?,
    title: String?,
    description: String?,
    onClick: () -> Unit
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
                    onClick()
//                    navController.currentBackStackEntry?.savedStateHandle?.set(com.levox.common.utils.Constants.ARTICLE, article)
//                    navController.navigate(com.levox.common.utils.Constants.DETAIL_SCREEN)
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row {
                    ArticleImage(imageUrl = imageUrl, 100, .5f)
                    Column(
                        modifier = Modifier
                            .padding(start = 4.dp)
                    ) {
                        Text(text = "Author: ${author ?: "Unknown"} ")
                        Text(
                            text = "Published at: $publishDate",
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = title ?: "Title not found",
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = description ?: "Empty description",
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}