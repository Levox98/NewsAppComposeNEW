package com.levox.navigation

sealed class MainNavigationGraph : BaseNav() {
    object Root : MainNavigationGraph() {
        override val route: String
            get() = "Main"
        override val level: NavigationLevel
            get() = NavigationLevel.ground

    }

    class Main(
        override val popUpTo: BaseNav? = null
    ) : MainNavigationGraph() {
        override val route: String = "Search"
        override val first: Boolean = false
        override val level: NavigationLevel
            get() = NavigationLevel.main

    }

    class Article(
        override val popUpTo: BaseNav? = null
    ) : MainNavigationGraph() {
        override val route: String = "Article"
        override val first: Boolean = false
        override val level: NavigationLevel
            get() = NavigationLevel.main
    }
}
