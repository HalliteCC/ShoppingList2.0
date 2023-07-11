package com.example.buylist2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buylist2.model.ItemsModel
import com.example.buylist2.repositories.ItemsRepository

class ItemsViewModel (application: Application) : AndroidViewModel(application){

    private val productsRepository = ItemsRepository(application.applicationContext)

    private val _product = MutableLiveData <List<ItemsModel>>()
    val product : LiveData <List<ItemsModel>> = _product



    fun delete(id: Int){
        productsRepository.deleteList(id)
    }
    fun getAll(){
        _product.value = productsRepository.getAll()
    }

    fun status(id: Int, complete: Boolean) {
        val productToUpdate = product.value?.find { it.id == id }
        productToUpdate?.let {
            it.complete = complete
            productsRepository.updateProducts(it)
            _product.value = product.value // Notifica as alterações para o fragmento
        }
    }

    fun getItemById(selectedListId: Int) {
        _product.value = productsRepository.getAllProducts(selectedListId)
    }
}