package com.quandoo.reservetables.data.api

import com.quandoo.reservetables.data.model.Table
import io.reactivex.Flowable
import retrofit2.http.GET

interface TableService {

    @GET("/quandoo-assessment/table-map.json")
    fun updateTables(): Flowable<BooleanArray>

}