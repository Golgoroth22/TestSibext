package com.vfalin.sibext

import android.app.Application
import com.vfalin.sibext.di.modules.appModule
import com.vfalin.sibext.di.modules.filmsActivityModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class ProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ProjectApplication)
            modules(listOf(appModule, filmsActivityModule))
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}