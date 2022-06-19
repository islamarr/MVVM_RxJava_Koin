package com.islam.music

import android.app.Application
import com.islam.music.common.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class MusicApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MusicApp)
            modules(appModule, databaseModule, apiModule, viewModelModule, useCaseModule)
            modules(ViewModelModule().module, UseCaseModule().module, ApiModule().module) // Koin Annotations
        }
    }
}