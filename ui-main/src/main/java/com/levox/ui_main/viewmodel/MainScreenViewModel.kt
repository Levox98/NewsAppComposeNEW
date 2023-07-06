package com.levox.ui_main.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.levox.common.utils.BaseViewModel
import com.levox.domain.entity.Article
import com.levox.domain.usecase.GetEverythingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val MIN_LENGTH = 3
private const val MAX_LENGTH = 50

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getEverythingUseCase: GetEverythingUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state: StateFlow<MainScreenState>
        get() = _state.asStateFlow()

    val searchQuery: MutableState<String> = mutableStateOf("")

    private fun searchNews(searchQuery: String) {
        viewModelScope.launch {
            _state.update { currentState -> currentState.copy(isLoading = true) }
            try {
                viewModelScope.launch(Dispatchers.IO) {
                    getEverythingUseCase(searchQuery).collect { state ->
                        withContext(Dispatchers.Main) {
                            when (state) {
                                is GetEverythingUseCase.State.Loading -> {
                                    _state.update { currentState ->
                                        currentState.copy(isLoading = true)
                                    }
                                }
                                is GetEverythingUseCase.State.Error -> {
                                    _state.update { currentState ->
                                        currentState.copy(
                                            isLoading = false,
                                            error = Exception(state.error.message())
                                        )
                                    }
                                }
                                is GetEverythingUseCase.State.Success -> {
                                    _state.update { currentState ->
                                        currentState.copy(
                                            isLoading = false,
                                            searchedNews = state.articles ?: emptyList()
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                _state.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        error = e,
                        searchedNews = emptyList()
                    )
                }
            }
        }
    }

    fun updateQuery(query: String) {
        if (query.isEmpty()) {
            _state.update { currentState -> currentState.copy(queryValid = true) }
        }
        searchQuery.value = query
    }

    fun clearSearch() {
        _state.update { currentState ->
            currentState.copy(searchedNews = emptyList(), queryValid = true)
        }
    }

    fun onSearchClicked(query: String) {
        _state.update { currentState -> currentState.copy(queryValid = checkQueryValid(query)) }
        if (!_state.value.queryValid) return
        searchNews(query)
    }

    private fun checkQueryValid(query: String): Boolean {
        return query.length in MIN_LENGTH..MAX_LENGTH
    }
}

data class MainScreenState(
    val isLoading: Boolean = false,
    val error: Exception? = null,
    val searchedNews: List<Article> = emptyList(),
    val queryValid: Boolean = true
)