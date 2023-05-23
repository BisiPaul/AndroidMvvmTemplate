package com.applakazam.androidmvvmtemplate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import com.applakazam.androidmvvmtemplate.BuildConfig

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}