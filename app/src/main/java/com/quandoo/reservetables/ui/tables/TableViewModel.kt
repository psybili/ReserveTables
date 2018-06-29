package com.quandoo.reservetables.ui.tables

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.quandoo.reservetables.data.model.Reservation
import com.quandoo.reservetables.data.model.Table
import com.quandoo.reservetables.data.repository.ReservationRepository
import com.quandoo.reservetables.data.repository.TableRepository
import com.quandoo.reservetables.util.ext.toLiveData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TableViewModel @Inject constructor(
        private val tableRepository: TableRepository,
        private val reservationRepository: ReservationRepository
) : ViewModel() {
    val tables: LiveData<List<Table>> = tableRepository.list
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(Flowable.empty())
            .toLiveData()

    fun updateTableList() {
        tableRepository.updateTables()
                .map {
                    var id = 0L
                    for (boolean: Boolean in it) {
                        tableRepository.create(Table(id++, boolean))
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Flowable.empty())
                .subscribe()
    }

    fun createReservations(reservation: Reservation) {
        Completable.create { reservationRepository.create(reservation) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}