package com.quandoo.reservetables.ui.reservations

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.quandoo.reservetables.R
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import com.quandoo.reservetables.util.ext.setContentFragment
import javax.inject.Inject

class ReservationsActivity : AppCompatActivity(), HasSupportFragmentInjector {

  @Inject lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

  override fun supportFragmentInjector() = androidInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.reservations_activity)
    setContentFragment(R.id.containerLayout) { ReservationsFragment.newInstance() }
  }
}
