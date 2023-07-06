package com.levox.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.levox.newsapp.ui.app.NewsApp
import com.levox.newsapp.ui.theme.NewsAppComposeTheme
import com.levox.newsapp.ui.viewmodel.NewsViewModel

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppComposeTheme {
                navController = rememberNavController()

                NewsApp(navController = navController, viewModel = viewModel)
            }
        }
    }
}
