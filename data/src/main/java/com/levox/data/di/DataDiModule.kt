package com.levox.data.di

import com.levox.data.repository.NewsRepositoryImpl
import com.levox.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataDiModule {
    @Singleton
    @Provides
    fun bindNewsRepository(r: NewsRepositoryImpl): NewsRepository = r
}