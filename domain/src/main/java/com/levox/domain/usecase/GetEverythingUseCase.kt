package com.levox.domain.usecase

import com.levox.common.utils.AppError
import com.levox.domain.entity.Article
import com.levox.domain.repository.NewsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEverythingUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(query: String) = flow {
        emit(State.Loading)

        val articles = newsRepository.getAllNews(query)

        articles.onError {
            emit(State.Error(it))
        }

        articles.onSuccess {
            emit(State.Success(it))
        }
    }

    sealed class State {
        object Loading : State()
        class Error(val error: AppError) : State()
        class Success(val articles: List<Article>?) : State()
    }
}