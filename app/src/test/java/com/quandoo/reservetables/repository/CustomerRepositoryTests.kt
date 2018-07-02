package com.quandoo.reservetables.repository

import com.quandoo.reservetables.RxImmediateSchedulerRule
import com.quandoo.reservetables.data.api.CustomerService
import com.quandoo.reservetables.data.model.Customer
import com.quandoo.reservetables.data.model.dao.CustomerDao
import com.quandoo.reservetables.data.repository.CustomerRepository
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit

@RunWith(JUnit4::class)
class CustomerRepositoryTests {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var customerService: CustomerService

    @Mock
    private lateinit var customerDao: CustomerDao

    private lateinit var customerRepository: CustomerRepository

    @Before
    fun setup() {
        customerRepository = CustomerRepository(customerService, customerDao)
    }

    @Test
    fun updateCustomersShouldReturnCustomerList() {
        val c1 = Customer("fn1", "ln1", 1)
        val c2 = Customer("fn2", "ln2", 2)
        `when`(customerService.updateCustomers()).thenReturn(Flowable.just(listOf(c1, c2)))

        val result = customerRepository.updateCustomers()

        val testSubscriber = TestSubscriber<List<Customer>>()
        result.subscribe(testSubscriber)
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)

        val listResult = testSubscriber.values()[0]
        Assert.assertEquals(listResult.size, 2)
        Assert.assertEquals(listResult[0].id, 1)
        Assert.assertEquals(listResult[1].id, 2)
    }

}