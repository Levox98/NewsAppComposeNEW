package com.levox.newsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.levox.navigation.MainNavigationGraph
import com.levox.ui_main.addMainScreen

@Composable
fun NewsAppScreen(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = MainNavigationGraph.Root.route,
        route = MainNavigationGraph.Main().route
    ) {
        addMainScreen(navHostController)
    }
}