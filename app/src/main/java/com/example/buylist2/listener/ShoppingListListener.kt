package com.example.buylist2.listener

interface ShoppingListListener {

    //Click para abrir fragment
    fun onOpenFragment(id: Int)


    //Click para edição
    fun onListClick(id: Int)

    //Remoção
    fun onDeleteClick(id: Int)

}