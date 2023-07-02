package com.example.buylist2.dao

import androidx.room.*
import com.example.buylist2.model.ItemsModel

@Dao
interface ItemsDAO {

    @Insert
    fun insertProduct(productsList: ItemsModel): Long

    @Delete
    fun deleteProduct(productsList: ItemsModel)

    @Update
    fun updateProduct (productsList: ItemsModel): Int

    @Query("SELECT * FROM ItemsList WHERE id = :id")
    fun get(id: Int): ItemsModel

    @Query("SELECT * FROM ItemsList")
    fun getAllProduct(): List<ItemsModel>

    @Query("SELECT * FROM ItemsList WHERE listId = :selectedListId")
    fun getAllProductsByListId(selectedListId: Int): List<ItemsModel>

}