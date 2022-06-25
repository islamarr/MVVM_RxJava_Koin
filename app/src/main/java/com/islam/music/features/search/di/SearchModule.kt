package com.islam.music.features.search.di

import com.islam.music.features.search.data.remote.datasource.SearchArtistDataSource
import com.islam.music.features.search.data.remote.datasource.SearchArtistDataSourceImpl
import com.islam.music.features.search.data.repositories.SearchArtistRepositoryImpl
import com.islam.music.features.search.domain.repositories.SearchArtistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class SearchModule {

    @Binds
    abstract fun bindSearchArtistRepository(repository: SearchArtistRepositoryImpl): SearchArtistRepository

    @Binds
    abstract fun bindSearchArtistRemoteDataSource(dataSource: SearchArtistDataSourceImpl): SearchArtistDataSource

}