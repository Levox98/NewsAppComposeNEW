package com.levox.newsapp.ui.app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.levox.newsapp.data.models.Article
import com.levox.newsapp.ui.app.component.ArticleItem
import com.levox.newsapp.ui.app.component.SearchBar
import com.levox.newsapp.ui.viewmodel.NewsViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: NewsViewModel
) {


    val resultList = mutableListOf<Article>()

    val state by viewModel.state.collectAsState()
    val query by viewModel.searchQuery

    val queryColor = if (state.queryValid) {
        Color.Transparent
    } else {
        Color.Red
    }

    if (!state.isLoading && state.searchedNews.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Введите тему для просмотра новостей"
            )
        }
    }


    if (state.searchedNews.isNotEmpty()) {
        resultList.clear()
        resultList.addAll(state.searchedNews)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top
    ) {
        SearchBar(
            text = query,
            onTextChange = { viewModel.updateQuery(it) },
            onCloseClicked = { viewModel.clearSearch() },
            onSearchClicked = { viewModel.onSearchClicked(it) },
            backgroundColor = queryColor
        )
        if (state.isLoading) {
            LoadingScreen()
        } else {
            LazyColumn {
                items(count = resultList.size) { index ->
                    ArticleItem(
                        article = resultList[index],
                        navController = navController
                    )
                }
            }
        }
    }
}