package com.quandoo.reservetables.ui.reservations.tables

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.quandoo.reservetables.R
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import com.quandoo.reservetables.util.ext.setContentFragment
import javax.inject.Inject

class TableActivity : AppCompatActivity(), HasSupportFragmentInjector {

// todo: remove  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  @Inject lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

  override fun supportFragmentInjector() = androidInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.table_activity)
    setContentFragment(R.id.containerLayout) { TableFragment.newInstance() }
  }
}
