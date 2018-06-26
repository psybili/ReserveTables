package com.quandoo.reservetables

import com.quandoo.reservetables.di.DaggerAppComponent
import com.quandoo.reservetables.di.RoomModule
import com.quandoo.reservetables.di.applyAutoInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class App : DaggerApplication() {

    @Inject
    lateinit var appLifecycleCallbacks: AppLifecycleCallbacks

    override fun applicationInjector() = DaggerAppComponent.builder()
            .application(this)
            .roomModule(RoomModule(this))
            .build()

    override fun onCreate() {
        super.onCreate()
        applyAutoInjector()
        appLifecycleCallbacks.onCreate(this)
    }

    override fun onTerminate() {
        appLifecycleCallbacks.onTerminate(this)
        super.onTerminate()
    }

}