package com.example.buylist2.ui.shoppingList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buylist2.model.ValidationModel

class ShoppingListViewModel (application: Application) : AndroidViewModel(application) {

    private val buyListRepository = BuyListRepository(application.applicationContext)



    private val _delete = MutableLiveData<ValidationModel>()
    val delete: LiveData<ValidationModel> = _delete

    private val _buy = MutableLiveData<List<BuyListModel>>()
    val buy: LiveData<List<BuyListModel>> = _buy
    val nameList = MutableLiveData<List<String>>()


    fun getAll() {
        _buy.value = buyListRepository.getAll()
    }

    fun delete(id: Int) {
        buyListRepository.deleteList(id)
    }

    fun spinner(): List<BuyListModel> {
        return buyListRepository.getAll()
    }

}