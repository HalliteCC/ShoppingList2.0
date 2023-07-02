package com.example.buylist2.repositories

import android.content.Context
import com.example.buylist2.R
import com.example.buylist2.listener.Listener
import com.example.buylist2.model.LoginModel
import com.example.buylist2.database.ShoppingDataBase

class LoginRepository(context: Context) : BaseRepository(context) {

    private var shoppingDataBase = ShoppingDataBase.getDataBase(context).loginDao()

    fun insert(login: LoginModel, listener: Listener<LoginModel>) {
        if (!isConnectionAvaliable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val id = shoppingDataBase.insert(login)
        if (id > 0) {
            listener.onSuccess(login)
        } else {
            listener.onFailure("This user is already registered")
        }
    }

    fun get(email: String, password: String): LoginModel {
        return shoppingDataBase.getUser(email, password)
    }

    fun login(email: String, password: String, listener: Listener<LoginModel>) {
        if (!isConnectionAvaliable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val user = shoppingDataBase.getUser(email, password)
        if (user == null) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        } else {
            listener.onSuccess(user)
        }
    }

}