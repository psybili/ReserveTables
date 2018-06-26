package com.quandoo.reservetables.ui.reservations.customers

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil.inflate
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quandoo.reservetables.R
import com.quandoo.reservetables.data.model.Customer
import com.quandoo.reservetables.databinding.CustomerFragmentBinding
import com.quandoo.reservetables.databinding.CustomerItemBinding
import com.quandoo.reservetables.di.Injectable
import com.quandoo.reservetables.util.ext.observe
import javax.inject.Inject

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
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        customerViewModel.updateCustomerList()

        customerViewModel.customers.observe(this) {
            it ?: return@observe
            adapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }
    }

    companion object {
        fun newInstance() = CustomerFragment()
    }
}

class CustomerAdapter : RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {
    val items = ArrayList<Customer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate<CustomerItemBinding>(
                LayoutInflater.from(parent.context), R.layout.customer_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.customer = items[position]
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(val binding: CustomerItemBinding) : RecyclerView.ViewHolder(binding.root)
}