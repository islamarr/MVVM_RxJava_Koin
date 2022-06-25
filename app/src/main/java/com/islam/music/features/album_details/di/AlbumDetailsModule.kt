package com.islam.music.features.album_details.di

import com.islam.music.features.album_details.data.db.datasource.AlbumDetailsLocalDataSource
import com.islam.music.features.album_details.data.db.datasource.AlbumDetailsLocalDataSourceImpl
import com.islam.music.features.album_details.data.remote.datasource.AlbumDetailsRemoteDataSource
import com.islam.music.features.album_details.data.remote.datasource.AlbumDetailsRemoteDataSourceImpl
import com.islam.music.features.album_details.data.repositories.AlbumDetailsRepositoryImpl
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class AlbumDetailsModule {

    @Binds
    abstract fun bindAlbumDetailsRepository(repository: AlbumDetailsRepositoryImpl): AlbumDetailsRepository

    @Binds
    abstract fun bindAlbumDetailsRemoteDataSource(dataSource: AlbumDetailsRemoteDataSourceImpl): AlbumDetailsRemoteDataSource

    @Binds
    abstract fun bindAlbumDetailsLocaleDataSource(dataSource: AlbumDetailsLocalDataSourceImpl): AlbumDetailsLocalDataSource

}