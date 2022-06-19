package com.islam.music

import android.app.Application
import com.islam.music.common.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MusicApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MusicApp)
            modules(appModule, databaseModule, apiModule, viewModelModule, useCaseModule)
        }
    }
}