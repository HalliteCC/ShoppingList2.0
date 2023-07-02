package com.example.buylist2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buylist2.databinding.RowItemListBinding
import com.example.buylist2.listener.ItemsListener
import com.example.buylist2.model.ItemsModel
import com.example.buylist2.viewHolder.ItemsViewHolder

class ItemListAdapter : RecyclerView.Adapter<ItemsViewHolder>() {

    private var productList: List<ItemsModel> = listOf()
    private lateinit var listener: ItemsListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RowItemListBinding.inflate(inflater, parent, false)
        return ItemsViewHolder(itemBinding, listener)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    fun attachListener(productListener: ItemsListener) {
        listener = productListener
    }

    fun updateProduct(products: List<ItemsModel>) {
        productList = products
        notifyDataSetChanged()
    }
}