package com.levox.common.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.levox.navigation.NavigationBack
import com.levox.navigation.NavigationLevel
import com.levox.navigation.NavigationManager

abstract class BaseViewModel(
    val navigationManager: NavigationManager
): ViewModel() {
    abstract val navigationLevel: NavigationLevel

    var showProgress: Boolean by mutableStateOf(false)
        private set

    var errorMessage: String? by mutableStateOf("")
        private set

    fun showProgress() {
        showProgress = true
    }

    fun hideProgress() {
        showProgress = false
    }

    fun error(message: String) {
        hideProgress()
        errorMessage = message
    }

    fun clearError() {
        errorMessage = null
    }

    open fun navigateBack(level: NavigationLevel? = null) {
        navigationManager.navigate(NavigationBack(level ?: navigationLevel))
    }
}