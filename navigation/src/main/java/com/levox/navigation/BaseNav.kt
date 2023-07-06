package com.levox.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

abstract class BaseNav {
    abstract val route: String
    protected open val url: String? = null
    protected open val first = false
    protected open val popUpTo: BaseNav? = null
    protected open val onceInStack = false
    abstract val level: NavigationLevel

    open fun navigate(navController: NavHostController) {
        navController.navigate(url ?: route) {
            when {
                first -> {
                    popUpTo("APPLICATION_ROOT") {
                        inclusive = true
                        saveState = true
                    }
                }
                this@BaseNav.popUpTo != null -> {
                    popUpTo(this@BaseNav.popUpTo!!.route) {
                        inclusive = false
                        saveState = true
                    }
                }
                onceInStack -> {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }
}

class NavigationBack(override val level: NavigationLevel) : BaseNav() {
    override val route: String = ""
    override fun navigate(navController: NavHostController) {
        navController.popBackStack()
    }
}

enum class NavigationLevel {
    ground, main
}