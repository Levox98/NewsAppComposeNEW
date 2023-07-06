package com.levox.newsapp.ui.app.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun ArticleImage(imageUrl: String?, verticalHeight: Int, widthFraction: Float) {
    SubcomposeAsyncImage(
        model = imageUrl,
        loading = {
            LinearProgressIndicator(
                color = Color.White,
                backgroundColor = Color.LightGray,
                modifier = Modifier
            )
        },
        contentDescription = "Article image",
        modifier = Modifier
            .fillMaxWidth(widthFraction)
            .height(verticalHeight.dp)
            .background(Color.White),
        contentScale = ContentScale.Crop,
    )
}