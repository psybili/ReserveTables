package com.quandoo.reservetables.data.api

import io.reactivex.Flowable
import com.quandoo.reservetables.data.model.Customer
import com.quandoo.reservetables.data.model.Table
import retrofit2.http.GET

interface ReservationService {

    @GET("/quandoo-assessment/customer-list.json")
    fun updateCustomers(): Flowable<List<Customer>>

    @GET("/quandoo-assessment/table-map.json")
    fun getTables(): Flowable<List<Table>>

}