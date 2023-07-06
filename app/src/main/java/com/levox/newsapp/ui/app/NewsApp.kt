package com.levox.newsapp.ui.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.levox.newsapp.data.models.Article
import com.levox.newsapp.ui.app.screens.DetailScreen
import com.levox.newsapp.ui.app.screens.MainScreen
import com.levox.newsapp.ui.viewmodel.NewsViewModel
import com.levox.newsapp.utils.Constants

@Composable
fun NewsApp(navController: NavHostController, viewModel: NewsViewModel) {
    NavHost(navController = navController, startDestination = Constants.MAIN_SCREEN) {
        composable(Constants.MAIN_SCREEN) {
            MainScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Constants.DETAIL_SCREEN) {
            val article =
                navController.previousBackStackEntry?.savedStateHandle?.get<Article>(Constants.ARTICLE)

            article?.let {
                DetailScreen(article = it)
            }
        }
    }
}