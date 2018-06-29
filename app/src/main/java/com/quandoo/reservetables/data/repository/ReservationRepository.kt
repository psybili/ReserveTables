package com.quandoo.reservetables.data.repository

import com.quandoo.reservetables.data.api.ReservationService
import com.quandoo.reservetables.data.model.Reservation
import com.quandoo.reservetables.data.model.dao.ReservationDao
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ReservationRepository @Inject constructor(
        private val reservationService: ReservationService,
        private val reservationDao: ReservationDao
) {

    val list: Flowable<List<Reservation>>
        get() = reservationDao.reservationList

    fun create(reservation: Reservation) = reservationDao.insert(reservation)

    fun read(customerLastName: String, tableId: Long): Flowable<Reservation> = reservationDao.getReservation(customerLastName, tableId)

    fun update(reservation: Reservation) = reservationDao.update(reservation)

    fun delete(reservation: Reservation) = reservationDao.delete(reservation)

    fun updateReservations() = reservationService.updateCustomers()

}
