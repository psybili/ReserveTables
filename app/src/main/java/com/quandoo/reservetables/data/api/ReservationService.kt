package com.quandoo.reservetables.data.api

import com.quandoo.reservetables.data.model.Customer
import io.reactivex.Flowable
import retrofit2.http.GET

interface ReservationService {

    @GET("/quandoo-assessment/customer-list.json")
    fun updateCustomers(): Flowable<List<Customer>>

}