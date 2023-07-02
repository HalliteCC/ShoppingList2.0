package com.example.buylist2.viewHolder

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.buylist2.databinding.RowShoppingListBinding
import com.example.buylist2.listener.ShoppingListListener
import com.example.buylist2.model.ShoppingListModel

class ShoppingListViewHolder(private val itemBinding: RowShoppingListBinding, private val listener: ShoppingListListener)
    : RecyclerView.ViewHolder(itemBinding.root) {

    fun bindData(list: ShoppingListModel) {
        itemBinding.textListName.text = list.listName

        itemBinding.textListName.setOnClickListener { listener.onOpenFragment(list.id) }

        itemBinding.imgEdit.setOnClickListener {listener.onEditClick(list.id) }

        itemBinding.textListName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção da Lista")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("sim") { dialog, which -> listener.onDeleteClick(list.id) }
                .setNegativeButton("não", null)
                .create()
                .show()
            true
        }
    }
}