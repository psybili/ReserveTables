package com.quandoo.reservetables.di

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.quandoo.reservetables.di.module.CustomerModule
import com.quandoo.reservetables.di.module.TableModule
import com.quandoo.reservetables.ui.reservations.customers.CustomerActivity
import com.quandoo.reservetables.ui.reservations.tables.TableActivity

@Module
internal abstract class UiModule {

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @ContributesAndroidInjector(modules = [CustomerModule::class])
  internal abstract fun contributeCustomerActivity(): CustomerActivity

  @ContributesAndroidInjector(modules = [TableModule::class])
  internal abstract fun contributeTableActivity(): TableActivity

}