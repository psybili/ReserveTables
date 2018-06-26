package com.quandoo.reservetables.ui.reservations.customers

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.quandoo.reservetables.data.model.Customer
import com.quandoo.reservetables.data.repository.CustomerRepository
import com.quandoo.reservetables.util.ext.toLiveData
import javax.inject.Inject

class CustomerViewModel @Inject constructor(private val customerRepository: CustomerRepository)
    : ViewModel() {
    val customers: LiveData<List<Customer>> = customerRepository.list
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(Flowable.empty())
            .toLiveData()

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