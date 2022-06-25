package com.islam.music.features.album_details.di

import android.content.Context
import com.islam.music.features.album_details.data.db.AlbumDao
import com.islam.music.features.album_details.data.db.AlbumDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AlbumDataBaseModule {

    @Provides
    @Singleton
    fun provideAlbumDao(db: AlbumDatabase): AlbumDao = db.getAlbumDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AlbumDatabase =
        AlbumDatabase.invoke(appContext)
}