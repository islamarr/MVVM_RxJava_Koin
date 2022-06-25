package com.islam.music.features.search.di

import com.islam.music.features.search.data.remote.api.SearchAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class SearchAPIModule {

    @Provides
    fun provideAPIService(retrofit: Retrofit): SearchAPIService {
        return retrofit.create(SearchAPIService::class.java)
    }

}