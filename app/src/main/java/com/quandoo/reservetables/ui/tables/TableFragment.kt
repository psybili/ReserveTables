package com.quandoo.reservetables.ui.tables

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import com.quandoo.reservetables.R
import com.quandoo.reservetables.data.model.Customer
import com.quandoo.reservetables.data.model.Reservation
import com.quandoo.reservetables.databinding.TableFragmentBinding
import com.quandoo.reservetables.di.Injectable
import com.quandoo.reservetables.ui.reservations.ReservationsActivity
import com.quandoo.reservetables.util.ext.observe
import javax.inject.Inject

private const val EXTRA_CUSTOMER_ID = "EXTRA_CUSTOMER_ID"
private const val EXTRA_CUSTOMER = "EXTRA_CUSTOMER"
private const val EXTRA_TABLE_ID = "EXTRA_TABLE_ID"

class TableFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tableViewModel: TableViewModel
    private lateinit var binding: TableFragmentBinding
    private val adapter = TableAdapter()

    private lateinit var extraCustomer: Customer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<TableFragmentBinding>(inflater, R.layout.table_fragment, container, false)
                    .also {
                        binding = it
                    }
                    .root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        extraCustomer = activity!!.intent.extras.getParcelable(EXTRA_CUSTOMER)

        tableViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(TableViewModel::class.java)

        adapter.itemClickListener =
                object : TableAdapter.ItemClickListener {
                    override fun onItemClick(tableId: Long) {
                        this@TableFragment.onItemClick(extraCustomer, tableId)
                    }
                }

        binding.tableRecyclerView.adapter = adapter
        binding.tableRecyclerView.layoutManager = GridLayoutManager(activity, 4, GridLayout.VERTICAL, false)

        tableViewModel.tables.observe(this) {
            it ?: return@observe
            adapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }

        tableViewModel.updateTableList()

    }

    private fun onItemClick(customer: Customer, tableId: Long) {
        val reservation = Reservation(customer.customerLastName, tableId)
        tableViewModel.createReservations(reservation)
        startReservationsActivity()
    }

    private fun startReservationsActivity() {
        val container = activity
        val i = Intent(container, ReservationsActivity::class.java)
        startActivity(i)
    }

    companion object {
        fun newInstance() = TableFragment()
    }
}

