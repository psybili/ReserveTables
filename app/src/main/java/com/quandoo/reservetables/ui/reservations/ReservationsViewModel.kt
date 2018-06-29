package com.quandoo.reservetables.ui.reservations

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.quandoo.reservetables.data.model.Reservation
import com.quandoo.reservetables.data.repository.ReservationRepository
import com.quandoo.reservetables.util.ext.toLiveData
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReservationsViewModel @Inject constructor(reservationRepository: ReservationRepository)
    : ViewModel() {
    val reservations: LiveData<List<Reservation>> = reservationRepository.list
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(Flowable.empty())
            .toLiveData()
}