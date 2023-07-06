package com.levox.ui_main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getEverythingUseCase: GetEverythingUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    var searchQuery by mutableStateOf("")
        private set

    private fun searchNews(searchQuery: String) {
        _state.update { currentState -> currentState.copy(isLoading = true) }
        try {
            getArticles(searchQuery)
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

    fun updateQuery(query: String) {
        _state.update { currentState -> currentState.copy(queryValid = true) }

        searchQuery = query
    }

    fun clearSearch() {
        _state.update { currentState ->
            currentState.copy(searchedNews = emptyList(), queryValid = true)
        }
    }

    fun onSearchClicked(query: String) {
        _state.update { currentState -> currentState.copy(queryValid = checkQueryValid(query)) }

        if (!_state.value.queryValid) return

        _state.update { currentState -> currentState.copy(searchWasPressed = true) }
        searchNews(query)
    }

    private fun checkQueryValid(query: String): Boolean {
        return query.length in MIN_QUERY_LENGTH..MAX_QUERY_LENGTH
    }

    private fun getArticles(searchQuery: String) {
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
    }
    
    companion object {
        private const val MIN_QUERY_LENGTH = 3
        private const val MAX_QUERY_LENGTH = 50
    }
}

data class MainScreenState(
    val isLoading: Boolean = false,
    val error: Exception? = null,
    val searchedNews: List<Article> = emptyList(),
    val queryValid: Boolean = true,
    val searchWasPressed: Boolean = false
)