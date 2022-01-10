package com.skillbox.mytestapp

import android.app.Application
import com.skillbox.mytestapp.di.dataModule
import com.skillbox.mytestapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class TestApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@TestApp)
            modules(listOf(dataModule,networkModule))
        }
        Timber.plant(Timber.DebugTree())
    }

}