package com.example.buylist2.repositories

import android.content.Context
import com.example.buylist2.database.ShoppingDataBase
import com.example.buylist2.model.ItemsModel

class ItemsRepository (context: Context) {

    private val shoppingDataBase = ShoppingDataBase.getDataBase(context).itemsDao()

    fun insertProduct(products: ItemsModel): Boolean {
        return shoppingDataBase.insertProduct(products) > 0
    }

    fun updateProducts(products: ItemsModel): Boolean {
        return shoppingDataBase.updateProduct(products) > 0
    }

    fun deleteList(id: Int) {
        val list = get(id)
        shoppingDataBase.deleteProduct(list)
    }

    fun get(id: Int): ItemsModel {
        return shoppingDataBase.get(id)
    }

    fun getAll(): List<ItemsModel>{
        return shoppingDataBase.getAllProduct()
    }

    fun updateProductStatus(id: Int, complete: Boolean) {
        val product = get(id)
        product.complete = complete
        updateProducts(product)
    }

    fun getAllProducts(selectedListId: Int): List<ItemsModel> {
        return shoppingDataBase.getAllProductsByListId(selectedListId)
    }
}