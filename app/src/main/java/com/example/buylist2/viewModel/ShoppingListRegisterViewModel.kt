package com.example.buylist2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.buylist2.model.ShoppingListModel
import com.example.buylist2.repositories.ShoppingListRepository

class ShoppingListRegisterViewModel (application: Application) : AndroidViewModel(application) {

    private val shoppingListRepository = ShoppingListRepository(application.applicationContext)

    private val _shoppingList = MutableLiveData<ShoppingListModel>()
    var shoppingList: LiveData<ShoppingListModel> = _shoppingList

    fun get(id: Int){
        _shoppingList.value = shoppingListRepository.get(id)
    }

    fun insert(buyList: ShoppingListModel) {
        shoppingListRepository.insertList(buyList)
    }

    fun update(buyList: ShoppingListModel) {
        shoppingListRepository.updateList(buyList)
    }
}