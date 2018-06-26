package com.quandoo.reservetables.ui.reservations.tables

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quandoo.reservetables.R
import com.quandoo.reservetables.data.model.Table
import com.quandoo.reservetables.databinding.TableFragmentBinding
import com.quandoo.reservetables.databinding.TableItemBinding
import com.quandoo.reservetables.di.Injectable
import com.quandoo.reservetables.util.ext.observe
import javax.inject.Inject

class TableFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tableViewModel: TableViewModel
    private lateinit var binding: TableFragmentBinding
    private val adapter = TableAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<TableFragmentBinding>(inflater, R.layout.table_fragment, container, false)
                    .also {
                        binding = it
                    }
                    .root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tableViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(TableViewModel::class.java)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        tableViewModel.tables.observe(this) {
            it ?: return@observe
            adapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }

    }

    companion object {
        fun newInstance() = TableFragment()
    }
}

class TableAdapter : RecyclerView.Adapter<TableAdapter.ViewHolder>() {
    val items = ArrayList<Table>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate<TableItemBinding>(
                LayoutInflater.from(parent.context), R.layout.table_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.table = items[position]
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(val binding: TableItemBinding) : RecyclerView.ViewHolder(binding.root)
}