package com.quandoo.reservetables.ui.customers

import android.databinding.DataBindingUtil.inflate
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.quandoo.reservetables.R
import com.quandoo.reservetables.data.model.Customer
import com.quandoo.reservetables.databinding.CustomerItemBinding

class CustomerAdapter : RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {
    val customerList = ArrayList<Customer>()
    lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener {
        fun onItemClick(id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate(
                LayoutInflater.from(parent.context),
                R.layout.customer_item,
                parent,
                false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(customerList[position]) {
            itemClickListener.onItemClick(it.customerLastName)
        }
    }

    override fun getItemCount() = customerList.size

    inner class ViewHolder(val binding: CustomerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: Customer, listener: (Customer) -> Unit) = with(binding) {
            binding.setVariable(com.android.databinding.library.baseAdapters.BR.customer, currentItem)
            binding.executePendingBindings()
            binding.root.setOnClickListener { listener(currentItem) }
        }
    }
}