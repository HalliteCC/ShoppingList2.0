package com.example.buylist2.repositories

import android.content.Context
import com.example.buylist2.database.ShoppingDataBase
import com.example.buylist2.model.ItemsModel

class ItemsRepository (context: Context) {

    private val loginDataBase = ShoppingDataBase.getDataBase(context).itemsDao()

    fun insertProduct(products: ItemsModel): Boolean {
        return loginDataBase.insertProduct(products) > 0
    }

    fun updateProducts(products: ItemsModel): Boolean {
        return loginDataBase.updateProduct(products) > 0
    }

    fun deleteList(id: Int) {
        val list = get(id)
        loginDataBase.deleteProduct(list)
    }

    fun get(id: Int): ItemsModel {
        return loginDataBase.get(id)
    }

    fun getAll(): List<ItemsModel>{
        return loginDataBase.getAllProduct()
    }

    fun updateProductStatus(id: Int, complete: Boolean) {
        val product = get(id)
        product.complete = complete
        updateProducts(product)
    }

    fun getAllProducts(selectedListId: Int): List<ItemsModel> {
        return loginDataBase.getAllProductsByListId(selectedListId)
    }
}