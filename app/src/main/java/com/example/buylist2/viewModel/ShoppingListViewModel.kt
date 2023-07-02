package com.example.buylist2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buylist2.model.ShoppingListModel
import com.example.buylist2.model.ValidationModel
import com.example.buylist2.repositories.ShoppingListRepository

class ShoppingListViewModel (application: Application) : AndroidViewModel(application) {

    private val shoppingListRepository = ShoppingListRepository(application.applicationContext)



    private val _delete = MutableLiveData<ValidationModel>()
    val delete: LiveData<ValidationModel> = _delete

    private val _buy = MutableLiveData<List<ShoppingListModel>>()
    val buy: LiveData<List<ShoppingListModel>> = _buy
    val nameList = MutableLiveData<List<String>>()


    fun getAll() {
        _buy.value = shoppingListRepository.getAll()
    }

    fun delete(id: Int) {
        shoppingListRepository.deleteList(id)
    }

    fun spinner(): List<ShoppingListModel> {
        return shoppingListRepository.getAll()
    }

}