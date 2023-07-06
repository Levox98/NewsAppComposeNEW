package com.levox.navigation

sealed class Screen(val route: String) {
    object Main : Screen("Main")
    object Article : Screen("Article")
}
