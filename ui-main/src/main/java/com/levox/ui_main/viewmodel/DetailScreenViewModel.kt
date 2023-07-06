package com.levox.ui_main.viewmodel

import com.levox.common.utils.BaseViewModel
import com.levox.navigation.NavigationLevel
import com.levox.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    navigationManager: NavigationManager
) : BaseViewModel(navigationManager) {
    override val navigationLevel: NavigationLevel
        get() = NavigationLevel.main
}