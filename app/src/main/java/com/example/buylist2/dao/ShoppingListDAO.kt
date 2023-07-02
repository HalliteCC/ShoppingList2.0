package com.example.buylist2.dao

import androidx.room.*
import com.example.buylist2.model.ShoppingListModel

@Dao
interface ShoppingListDAO {

    @Insert
    fun insertList(shoppingList: ShoppingListModel): Long

    @Delete
    fun deleteList(shoppingList: ShoppingListModel)

    @Update
    fun updateList (shoppingList: ShoppingListModel): Int

    @Query("SELECT * FROM ShoppingList WHERE id = :id")
    fun get(id: Int): ShoppingListModel

    @Query("SELECT * FROM ShoppingList")
    fun getList(): List<ShoppingListModel>
}