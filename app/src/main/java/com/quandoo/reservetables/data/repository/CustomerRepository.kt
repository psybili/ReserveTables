package com.quandoo.reservetables.data.repository

import com.quandoo.reservetables.data.api.ReservationService
import com.quandoo.reservetables.data.model.Customer
import com.quandoo.reservetables.data.model.dao.CustomerDao
import io.reactivex.Flowable
import javax.inject.Inject

class CustomerRepository @Inject constructor(
        private val reservationService: ReservationService,
        private val customerDao: CustomerDao
) {

    val list: Flowable<List<Customer>>
        get() = customerDao.customerList

    fun create(customer: Customer) = customerDao.insert(customer)

    fun read(id: Long): Flowable<Customer> = customerDao.getCustomerById(id)

    fun update(customer: Customer) = customerDao.update(customer)

    fun delete(customer: Customer) = customerDao.delete(customer)

    fun updateCustomers() = reservationService.updateCustomers()

}
