package com.quandoo.reservetables.ui.reservations.tables

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.quandoo.reservetables.data.model.Table
import com.quandoo.reservetables.data.repository.TableRepository
import com.quandoo.reservetables.util.ext.toLiveData
import javax.inject.Inject

class TableViewModel @Inject constructor(private val tableRepository: TableRepository)
    : ViewModel() {
    val tables: LiveData<List<Table>> = tableRepository.getTables()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(Flowable.empty())
            .toLiveData()

}