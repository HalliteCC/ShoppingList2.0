package com.example.buylist2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.buylist2.model.ItemsModel
import com.example.buylist2.model.ShoppingListModel
import com.example.buylist2.repositories.ItemsRepository
import com.example.buylist2.repositories.ShoppingListRepository

class ItemRegisterViewModel (application: Application) : AndroidViewModel(application) {


    //VIEWMODEL UTILIZADA PARA REGISTRO DO ITEM


    private val itemsRepository = ItemsRepository(application.applicationContext)

    private val _itemSave = MutableLiveData<ItemsModel>()
    var itemSave: LiveData<ItemsModel> = _itemSave

    fun get(id: Int) {
        _itemSave.value = itemsRepository.get(id)
    }

    fun insetItem(products: ItemsModel) {
        itemsRepository.insertProduct(products)
    }

    fun updateItem(products: ItemsModel) {
        itemsRepository.updateProducts(products)
    }
}