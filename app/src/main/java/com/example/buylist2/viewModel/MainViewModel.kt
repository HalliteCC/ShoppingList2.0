package com.example.buylist2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.buylist2.constant.ShoppingConstant
import com.example.buylist2.sharedpreference.SharedPreference

class MainViewModel (application: Application) : AndroidViewModel(application){

    private var sharedPreference = SharedPreference(application.applicationContext)

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    fun logout(){
        sharedPreference.remove(ShoppingConstant.LOGIN.KEY_EMAIL)
    }

}