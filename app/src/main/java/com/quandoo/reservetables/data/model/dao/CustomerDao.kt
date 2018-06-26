package com.quandoo.reservetables.data.model.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.quandoo.reservetables.data.model.Customer
import io.reactivex.Flowable

@Dao
interface CustomerDao : BaseDao<Customer> {

    @get:Query("SELECT * FROM Customer ORDER BY customerFirstName ASC")
    val customerList: Flowable<List<Customer>>

    @Query("SELECT * FROM Customer WHERE id = :id")
    fun getCustomerById(id: Long): Flowable<Customer>

}