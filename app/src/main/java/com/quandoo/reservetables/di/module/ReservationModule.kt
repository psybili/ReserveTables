package com.quandoo.reservetables.di.module

import android.arch.lifecycle.ViewModel
import com.quandoo.reservetables.di.ViewModelKey
import com.quandoo.reservetables.ui.reservations.ReservationsFragment
import com.quandoo.reservetables.ui.reservations.ReservationsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ReservationModule {

    @Binds
    @IntoMap
    @ViewModelKey(ReservationsViewModel::class)
    abstract fun bindReservationsViewModel(viewModel: ReservationsViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeReservationsFragment(): ReservationsFragment

}