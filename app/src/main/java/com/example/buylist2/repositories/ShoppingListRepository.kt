package com.example.buylist2.repositories

import android.content.Context
import com.example.buylist2.database.ShoppingDataBase
import com.example.buylist2.model.ShoppingListModel

class ShoppingListRepository(context: Context) {

    private val shoppingDataBase = ShoppingDataBase.getDataBase(context).shoppingDao()

    fun insertList(shoppingList: ShoppingListModel): Boolean {
        return shoppingDataBase.insertList(shoppingList) > 0
    }

    fun updateList(buyList: ShoppingListModel): Boolean {
        return shoppingDataBase.updateList(buyList) > 0
    }

    fun deleteList(id: Int) {
        val list = get(id)
        shoppingDataBase.deleteList(list)
    }

    fun get(id: Int): ShoppingListModel {
        return shoppingDataBase.get(id)
    }

    fun getAll(): List<ShoppingListModel>{
        return shoppingDataBase.getList()
    }
}