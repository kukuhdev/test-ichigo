package com.eve.testichigo.view.search.di

import com.eve.testichigo.core.di.NetworkModule
import com.eve.testichigo.view.search.data.remote.api.SearchApi
import com.eve.testichigo.view.search.data.repository.SearchRepositoryImpl
import com.eve.testichigo.view.search.domain.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class SearchModule {

    @Singleton
    @Provides
    fun provideSearchApi(retrofit: Retrofit) : SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchRepository(searchApi: SearchApi) : SearchRepository {
        return SearchRepositoryImpl(searchApi)
    }
}