package com.example.buylist2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoginViewModel(application: Application): AndroidViewModel (application) {

    private val loginRepository = loginRepository(application.applicationContext)


}