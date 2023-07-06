package com.levox.ui_main

import android.os.Build
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.levox.common.utils.AppConfig
import com.levox.domain.entity.Article
import com.levox.navigation.MainNavigationGraph
import com.levox.ui_main.screen.ArticleScreen
import com.levox.newsapp.ui.app.screens.MainScreen

fun NavGraphBuilder.addMainScreen(navHostController: NavHostController) {
    navigation(
        startDestination = MainNavigationGraph.Main().route,
        route = MainNavigationGraph.Root.route
    ) {
        composable(MainNavigationGraph.Main().route) {
            MainScreen(
                viewModel = hiltViewModel()
            )
        }
        composable(
            route = "${MainNavigationGraph.Article().route}/${AppConfig.Constants.ARTICLE}",
            arguments = listOf(
                navArgument(AppConfig.Constants.ARTICLE) {
                    type = NavType.ParcelableType(type = Article::class.java)
                }
            )
        ) {
            val article = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                navHostController.previousBackStackEntry?.arguments?.getSerializable(
                    AppConfig.Constants.ARTICLE,
                    Article::class.java
                )
            } else {
                navHostController.previousBackStackEntry?.arguments?.getSerializable(AppConfig.Constants.ARTICLE) as Article?
            }

            ArticleScreen(
                viewModel = hiltViewModel(),
                article = article
            )
        }
    }
}