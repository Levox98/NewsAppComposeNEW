package com.levox.ui_main.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.levox.ui_main.screen.component.ArticleItem
import com.levox.base_ui.component.SearchBar
import com.levox.domain.entity.Article
import com.levox.locale.R
import com.levox.navigation.Screen
import com.levox.newsapp.ui.app.screens.LoadingScreen
import com.levox.ui_main.viewmodel.MainScreenViewModel
import com.levox.ui_main.viewmodel.SharedViewModel

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController
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
                text = stringResource(id = R.string.search_suggestion)
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
                itemsIndexed(resultList) { _, article ->
                    ArticleItem(
                        article = article,
                        onClick = remember {
                            {
                                sharedViewModel.setCurrentArticle(it)
                                navHostController.navigate(Screen.Article.route)
                            }
                        }
                    )
                }
            }
        }
    }
}