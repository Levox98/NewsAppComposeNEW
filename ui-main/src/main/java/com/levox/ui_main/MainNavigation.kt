package com.levox.ui_main

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.levox.navigation.Screen
import com.levox.ui_main.screen.MainScreen
import com.levox.ui_main.screen.ArticleScreen
import com.levox.ui_main.viewmodel.SharedViewModel

fun NavGraphBuilder.addMainScreen(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    composable(Screen.Main.route) {
        MainScreen(
            viewModel = hiltViewModel(),
            sharedViewModel = sharedViewModel,
            navHostController = navHostController
        )
    }
    composable(Screen.Article.route) {
        ArticleScreen(
            viewModel = hiltViewModel(),
            sharedViewModel = sharedViewModel,
            navHostController = navHostController
        )
    }
}