package com.levox.newsapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levox.newsapp.data.models.Article
import com.levox.newsapp.data.network.NewsApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val MIN_LENGTH = 3
private const val MAX_LENGTH = 50

class NewsViewModel : ViewModel() {

    private val _state = MutableStateFlow(NewsUiState())
    val state: StateFlow<NewsUiState>
        get() = _state.asStateFlow()

    private val _articles = MutableLiveData<List<Article>>()

    val searchQuery: MutableState<String> = mutableStateOf("")

    private fun searchNews(searchQuery: String) {
        viewModelScope.launch {
            _articles.value = emptyList()
            _state.update { currentState -> currentState.copy(isLoading = true) }
            try {
                _articles.value =
                    NewsApi.retrofitService.searchNews(searchQuery = searchQuery).articles
                _state.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        searchedNews = _articles.value ?: emptyList()
                    )
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

data class NewsUiState(
    val isLoading: Boolean = false,
    val error: Exception? = null,
    val searchedNews: List<Article> = emptyList(),
    val queryValid: Boolean = true
)