package com.skillbox.mytestapp

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import androidx.viewbinding.BuildConfig
import com.skillbox.mytestapp.di.dataBaseModule
import com.skillbox.mytestapp.di.dataModule
import com.skillbox.mytestapp.di.networkModule
import com.example.core.utils.notifications.NotificationChannels
import com.skillbox.mytestapp.di.dataMapModule
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
            modules(listOf(dataModule,dataMapModule,networkModule,dataBaseModule))
        }
        Timber.plant(Timber.DebugTree())
        NotificationChannels.create(this)
        NotificationManagerCompat.from(this).cancelAll()
    }

}