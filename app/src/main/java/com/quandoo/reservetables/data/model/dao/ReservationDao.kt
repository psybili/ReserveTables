package com.quandoo.reservetables.data.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.quandoo.reservetables.data.model.Reservation
import io.reactivex.Flowable

@Dao
interface ReservationDao : BaseDao<Reservation> {

    @get:Query("SELECT * FROM Reservation ORDER BY customerLastName ASC")
    val reservationList: Flowable<List<Reservation>>

    @Query("SELECT * FROM Reservation WHERE customerLastName = :customerLastName AND tableId = :tableId AND expirationDate>datetime('now')")
    fun getReservation(customerLastName: String, tableId: Long): Flowable<Reservation>

}