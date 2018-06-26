package com.quandoo.reservetables.data.repository

import com.quandoo.reservetables.data.api.ReservationService
import javax.inject.Inject

class CustomerRepository @Inject constructor(private val reservationService: ReservationService) {

    fun getCustomers() = reservationService.getCustomers()

}
