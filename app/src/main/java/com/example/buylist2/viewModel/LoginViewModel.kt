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
import com.example.buylist2.repositories.constant.ShoppingConstant
import com.example.buylist2.repositories.securitypreference.SharedPreference

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    //Access to the repository
    private val loginRepository = LoginRepository(application.applicationContext)

    //Keep the register information
    private val sharedPreferences = SharedPreference(application.applicationContext)

    private val _login = MutableLiveData<ValidationModel>()
    val login: LiveData<ValidationModel> = _login

//Function to do verification and store the data on sharedPreference to do Login
    fun doLogin(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            ValidationModel(getApplication<Application>().applicationContext.getString(R.string.fill_all_fields))
            return
        }

        loginRepository.login(email, password, object : Listener<LoginModel> {
            override fun onSuccess(result: LoginModel) {
                sharedPreferences.store(ShoppingConstant.LOGIN.KEY_EMAIL, email)
                sharedPreferences.store(ShoppingConstant.LOGIN.KEY_PASS, password)

                _login.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _login.value =
                    ValidationModel((getApplication<Application>().applicationContext.getString(R.string.ERROR_LOGIN)))
            }

        })

    }

    //Function to check if the user is logged
    fun verifyLogin() {
        val getEmail = sharedPreferences.get(ShoppingConstant.LOGIN.KEY_EMAIL)

        if (getEmail != "") {
            _login.value = ValidationModel()
        }
    }
}

