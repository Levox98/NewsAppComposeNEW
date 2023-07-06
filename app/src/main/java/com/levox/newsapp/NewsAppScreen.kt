package com.levox.newsapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.levox.navigation.Screen
import com.levox.ui_main.addMainScreen
import com.levox.ui_main.viewmodel.SharedViewModel

@Composable
fun NewsAppScreen(navHostController: NavHostController) {

    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navHostController,
        startDestination = Screen.Main.route
    ) {
        addMainScreen(navHostController, sharedViewModel)
    }
}