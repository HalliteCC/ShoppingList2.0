package com.example.buylist2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.buylist2.R
import com.example.buylist2.listener.Listener
import com.example.buylist2.model.LoginModel
import com.example.buylist2.model.ValidationModel
import com.example.buylist2.repositories.LoginRepository
import com.example.buylist2.constant.ShoppingConstant
import com.example.buylist2.sharedpreference.SharedPreference

class UserRegisterViewModel (application: Application) : AndroidViewModel(application) {

    //Access to the repository
    private val loginRepository = LoginRepository(application.applicationContext)

    //Keep the register information
    private val sharedPreferences = SharedPreference(application.applicationContext)


    private val _register = MutableLiveData<ValidationModel>()
    val register: LiveData<ValidationModel> = _register


    fun get(email: String, password: String): LoginModel {
        return loginRepository.get(email, password)
    }

    fun create(email: String, password: String) {
        val verification = loginRepository.get(email, password)
        if (verification != null) {
            _register.value =
                ValidationModel(getApplication<Application>().applicationContext.getString(R.string.already_registered))
            return
        }
        if (email.isBlank() || password.isBlank()) {
            _register.value =
                ValidationModel(getApplication<Application>().applicationContext.getString(R.string.fill_all_fields))
            return
        }
        loginRepository.insert(LoginModel(email = email, password = password),
            object : Listener<LoginModel> {
                override fun onSuccess(result: LoginModel) {
                    sharedPreferences.store(ShoppingConstant.LOGIN.KEY_EMAIL, email)

                    _register.value = ValidationModel()

                }

                override fun onFailure(message: String) {
                    _register.value = ValidationModel(message)
                }
            })
    }
}