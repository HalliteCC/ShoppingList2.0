package com.example.buylist2.viewHolder

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.buylist2.R
import com.example.buylist2.databinding.RowItemListBinding
import com.example.buylist2.listener.ItemsListener
import com.example.buylist2.model.ItemsModel

class ItemsViewHolder (private val itemBinding : RowItemListBinding, private val listener: ItemsListener)
    : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(list: ItemsModel){
        itemBinding.textProduct.text = list.item
        itemBinding.TotalPrice.text = list.totalPrice.toString()
        itemBinding.textPrice.text = list.price.toString()
        itemBinding.textQuantity.text = list.quantity.toString()


        if (list.complete){
            itemBinding.imageTask.setImageResource(R.drawable.ic_done)
        }else {
            itemBinding.imageTask.setImageResource(R.drawable.ic_todo)
        }

        itemBinding.imageTask.setOnClickListener {
            val productId = list.id
            val isComplete = list.complete

            if (isComplete) {
                listener.onUndoClick(productId)
            } else {
                listener.onCompleteClick(productId)
            }
        }



        itemBinding.textProduct.setOnClickListener { listener.onEditClick(list.id) }

        itemBinding.textProduct.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção da Produto")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("sim") { dialog, which -> listener.onDelete(list.id) }
                .setNegativeButton("não", null)
                .create()
                .show()
            true
        }
    }
}