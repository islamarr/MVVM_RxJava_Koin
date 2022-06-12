package com.islam.music.features.top_albums.di

import com.islam.music.features.top_albums.data.remote.api.TopAlbumsAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class TopAlbumsAPIModule {

    @Provides
    fun provideAPIService(retrofit: Retrofit): TopAlbumsAPIService {
        return retrofit.create(TopAlbumsAPIService::class.java)
    }

}