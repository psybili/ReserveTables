package com.quandoo.reservetables.ui.customers

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil.inflate
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quandoo.reservetables.R
import com.quandoo.reservetables.data.model.Customer
import com.quandoo.reservetables.databinding.CustomerFragmentBinding
import com.quandoo.reservetables.di.Injectable
import com.quandoo.reservetables.ui.tables.TableActivity
import com.quandoo.reservetables.util.ext.observe
import javax.inject.Inject

private const val EXTRA_CUSTOMER = "EXTRA_CUSTOMER"

class CustomerFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var customerViewModel: CustomerViewModel
    private lateinit var binding: CustomerFragmentBinding
    private val adapter = CustomerAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflate<CustomerFragmentBinding>(
                    inflater,
                    R.layout.customer_fragment,
                    container,
                    false
            ).also { binding = it }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        customerViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(CustomerViewModel::class.java)

        adapter.itemClickListener =
                object : CustomerAdapter.ItemClickListener {
                    override fun onItemClick(customer: Customer) {
                        this@CustomerFragment.onItemClick(customer)
                    }
                }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        customerViewModel.customers.observe(this) {
            it ?: return@observe
            adapter.run {
                customerList.clear()
                customerList.addAll(it)
                notifyDataSetChanged()
            }
        }

        customerViewModel.updateCustomerList()
    }

    private fun onItemClick(customer: Customer) {
        startTablesActivity(customer)
    }

    fun startTablesActivity(customer: Customer) {
        val container = activity
        val i = Intent(container, TableActivity::class.java)
        i.putExtra(EXTRA_CUSTOMER, customer)
        startActivity(i)

    }

    companion object {
        fun newInstance() = CustomerFragment()
    }
}

