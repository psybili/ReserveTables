package com.quandoo.reservetables.data.repository

import com.quandoo.reservetables.data.api.ReservationService
import javax.inject.Inject

class TableRepository @Inject constructor(private val reservationService: ReservationService) {

    fun getTables() = reservationService.getTables()

}
