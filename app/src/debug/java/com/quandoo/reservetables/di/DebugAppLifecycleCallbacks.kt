package com.quandoo.reservetables.di

import android.app.Application
import com.quandoo.reservetables.AppLifecycleCallbacks
import timber.log.Timber

class DebugAppLifecycleCallbacks : AppLifecycleCallbacks {

    override fun onCreate(application: Application) {
        Timber.plant(Timber.DebugTree())
    }

    override fun onTerminate(application: Application) {

    }
}
