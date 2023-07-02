package com.example.buylist2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buylist2.databinding.RowShoppingListBinding
import com.example.buylist2.listener.ShoppingListListener
import com.example.buylist2.model.ShoppingListModel
import com.example.buylist2.viewHolder.ShoppingListViewHolder

class ShoppingListAdapter : RecyclerView.Adapter<ShoppingListViewHolder>() {

    private var buyList: List<ShoppingListModel> = listOf()
    private lateinit var listener: ShoppingListListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = RowShoppingListBinding.inflate(inflater, parent, false)


        return ShoppingListViewHolder(item, listener)
    }

    override fun getItemCount(): Int {
        return buyList.count()
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.bindData(buyList[position])
    }

    fun attachListener(buyListener: ShoppingListListener) {
        listener = buyListener
    }

    fun updateTasks(list: List<ShoppingListModel>){
        buyList = list
        notifyDataSetChanged()
    }
}