package com.quandoo.reservetables.ui.reservations

import android.databinding.DataBindingUtil.inflate
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.quandoo.reservetables.R
import com.quandoo.reservetables.data.model.Customer
import com.quandoo.reservetables.data.model.Reservation
import com.quandoo.reservetables.databinding.CustomerItemBinding
import com.quandoo.reservetables.databinding.ReservationItemBinding

class ReservationAdapter : RecyclerView.Adapter<ReservationAdapter.ViewHolder>() {
    val reservationList = ArrayList<Reservation>()
    lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener {
        fun onItemClick(reservation: Reservation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate(
                LayoutInflater.from(parent.context),
                R.layout.reservation_item,
                parent,
                false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reservationList[position]) {
            itemClickListener.onItemClick(it)
        }
    }

    override fun getItemCount() = reservationList.size

    inner class ViewHolder(val binding: ReservationItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: Reservation, listener: (Reservation) -> Unit) = with(binding) {
            binding.setVariable(com.android.databinding.library.baseAdapters.BR.reservation, currentItem)
            binding.executePendingBindings()
            binding.root.setOnClickListener { listener(currentItem) }
        }
    }
}