package com.levox.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor() {
    val commands = MutableStateFlow<BaseNav?>(null)
    fun setNull() {
        commands.value = null
    }
    fun navigate(command: BaseNav) {
        commands.value = command
    }
    fun back(level: NavigationLevel) {
        commands.value = NavigationBack(level)
    }
}