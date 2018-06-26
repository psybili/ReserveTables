package com.quandoo.reservetables.di

import dagger.Module
import dagger.Provides
import com.quandoo.reservetables.AppLifecycleCallbacks
import javax.inject.Singleton

@Module(includes = [DataModule::class])
internal object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks = DebugAppLifecycleCallbacks()

}