package com.quandoo.reservetables.di.module

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import com.quandoo.reservetables.di.ViewModelKey
import com.quandoo.reservetables.ui.reservations.tables.TableFragment
import com.quandoo.reservetables.ui.reservations.tables.TableViewModel

@Module
internal abstract class TableModule {

  @Binds
  @IntoMap
  @ViewModelKey(TableViewModel::class)
  abstract fun bindTableViewModel(viewModel: TableViewModel): ViewModel

  @ContributesAndroidInjector
  abstract fun contributeTableFragment(): TableFragment

}