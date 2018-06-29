package com.quandoo.reservetables.ui.tables

import android.databinding.DataBindingUtil.inflate
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.quandoo.reservetables.R
import com.quandoo.reservetables.data.model.Table
import com.quandoo.reservetables.databinding.TableItemBinding

class TableAdapter : RecyclerView.Adapter<TableAdapter.ViewHolder>() {

    val items = ArrayList<Table>()

    lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener {
        fun onItemClick(tableId: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate(
                LayoutInflater.from(parent.context),
                R.layout.table_item,
                parent,
                false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position]) {
            itemClickListener.onItemClick(it.id)
        }
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(val binding: TableItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
                currentItem: Table,
                listener: (Table) -> Unit
        ) = with(binding) {
            binding.setVariable(com.android.databinding.library.baseAdapters.BR.table, currentItem)
            binding.executePendingBindings()
            binding.tableButton.isEnabled = currentItem.available
            binding.tableButton.setOnClickListener { listener(currentItem) }
        }
    }
}