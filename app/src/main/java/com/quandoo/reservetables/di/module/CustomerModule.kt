package com.quandoo.reservetables.di.module

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import com.quandoo.reservetables.di.ViewModelKey
import com.quandoo.reservetables.ui.reservations.customers.CustomerViewModel
import com.quandoo.reservetables.ui.reservations.customers.CustomerFragment

@Module
internal abstract class CustomerModule {

  @Binds
  @IntoMap
  @ViewModelKey(CustomerViewModel::class)
  abstract fun bindCustomerViewModel(viewModel: CustomerViewModel): ViewModel

  @ContributesAndroidInjector
  abstract fun contributeCustomerFragment(): CustomerFragment

}