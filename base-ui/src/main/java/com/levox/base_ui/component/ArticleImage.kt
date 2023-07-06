package com.levox.base_ui.component

import androidx.compose.foundation.background
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.SubcomposeAsyncImage

@Composable
fun ArticleImage(
    modifier: Modifier = Modifier,
    imageUrl: String?
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        loading = {
            LinearProgressIndicator(
                color = Color.White,
                backgroundColor = Color.LightGray,
                modifier = Modifier
            )
        },
        contentDescription = null,
        modifier = modifier
            .background(Color.White),
        contentScale = ContentScale.Crop,
    )
}