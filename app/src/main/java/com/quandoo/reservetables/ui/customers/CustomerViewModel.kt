package com.quandoo.reservetables.ui.customers

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.quandoo.reservetables.data.model.Customer
import com.quandoo.reservetables.data.model.Reservation
import com.quandoo.reservetables.data.repository.CustomerRepository
import com.quandoo.reservetables.data.repository.ReservationRepository
import com.quandoo.reservetables.util.ext.toLiveData
import javax.inject.Inject

class CustomerViewModel @Inject constructor(
        private val customerRepository: CustomerRepository,
        private var reservationRepository: ReservationRepository)
    : ViewModel() {

    // holds customers read from db
    val customers: LiveData<List<Customer>> = toCustomersLiveData()

    // holds reservations read from db
    val reservations: LiveData<List<Reservation>> = toReservationsLiveData()

    private fun toCustomersLiveData(): LiveData<List<Customer>> {
        return customerRepository.list
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Flowable.empty())
                .toLiveData()
    }

    private fun toReservationsLiveData(): LiveData<List<Reservation>> {
        return reservationRepository.list
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Flowable.empty())
                .toLiveData()
    }

    // fetch remote Customer's and persist them locally
    fun updateCustomerList() {
        customerRepository.updateCustomers()
                .map {
                    for(customer: Customer in it) {
                        customerRepository.create(customer)
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Flowable.empty())
                .subscribe()
    }

}