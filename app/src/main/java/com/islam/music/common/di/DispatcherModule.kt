package com.islam.music.common.di

import com.islam.music.common.AppCoroutineDispatchers
import com.islam.music.common.AppCoroutineDispatchersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds
    abstract fun bindDispatchers(dispatchers: AppCoroutineDispatchersImpl): AppCoroutineDispatchers
}