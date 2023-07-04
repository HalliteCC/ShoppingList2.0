package com.example.buylist2.viewHolder

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.buylist2.R
import com.example.buylist2.databinding.RowItemListBinding
import com.example.buylist2.listener.ItemsListener
import com.example.buylist2.model.ItemsModel

class ItemsViewHolder (private val productBinding : RowItemListBinding, private val listener: ItemsListener)
    : RecyclerView.ViewHolder(productBinding.root) {

    fun bind(list: ItemsModel){
        productBinding.textProduct.text = list.item
        productBinding.TotalPrice.text = list.totalPrice.toString()
        productBinding.textPrice.text = list.price.toString()
        productBinding.textQuantity.text = list.quantity.toString()


        if (list.complete){
            productBinding.imageTask.setImageResource(R.drawable.ic_done)
        }else {
            productBinding.imageTask.setImageResource(R.drawable.ic_todo)
        }

        productBinding.imageTask.setOnClickListener(View.OnClickListener {
            val productId = list.id
            val isComplete = list.complete

            if (isComplete) {
                listener.onUndoClick(productId)
            } else {
                listener.onCompleteClick(productId)
            }
        })



        productBinding.textProduct.setOnClickListener { listener.onClick(list.id) }

        productBinding.textProduct.setOnLongClickListener {
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