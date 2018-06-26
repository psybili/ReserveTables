package com.quandoo.reservetables.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.quandoo.reservetables.App
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    RoomModule::class,
    UiModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun roomModule(roomModule: RoomModule): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}