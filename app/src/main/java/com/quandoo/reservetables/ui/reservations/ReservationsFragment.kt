package com.quandoo.reservetables.ui.reservations

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil.inflate
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.quandoo.reservetables.R
import com.quandoo.reservetables.data.model.Reservation
import com.quandoo.reservetables.databinding.ReservationsFragmentBinding
import com.quandoo.reservetables.di.Injectable
import com.quandoo.reservetables.util.ext.observe
import javax.inject.Inject

class ReservationsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var reservationsViewModel: ReservationsViewModel
    private lateinit var binding: ReservationsFragmentBinding
    private val adapter = ReservationAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflate<ReservationsFragmentBinding>(
                    inflater,
                    R.layout.reservations_fragment,
                    container,
                    false
            ).also { binding = it }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        reservationsViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(ReservationsViewModel::class.java)

        adapter.itemClickListener =
                object : ReservationAdapter.ItemClickListener {
                    override fun onItemClick(reservation: Reservation) {
                        this@ReservationsFragment.onItemClick(reservation)
                    }
                }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        reservationsViewModel.reservations.observe(this) {
            it ?: return@observe
            adapter.run {
                reservationList.clear()
                reservationList.addAll(it)
                notifyDataSetChanged()
            }
        }
    }

    private fun onItemClick(reservation: Reservation) {
        Toast.makeText(activity, "Table ${reservation.tableId} is reserved for: ${reservation.customerLastName}\nexpiration time: ${reservation.expirationDate}", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance() = ReservationsFragment()
    }
}

