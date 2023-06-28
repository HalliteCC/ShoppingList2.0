package com.example.buylist2.repositories

import android.content.Context
import com.example.buylist2.R
import com.example.buylist2.listener.Listener
import com.example.buylist2.model.LoginModel
import com.example.buylist2.repositories.database.DataBase

class LoginRepository(context: Context) : BaseRepository(context) {

    private var dataBase = DataBase.getDataBase(context).loginDao()

    fun insert(login: LoginModel, listener: Listener<LoginModel>) {
        if (isConnectionAvaliable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val id = dataBase.insert(login)
        if (id > 0) {
            listener.onSuccess(login)
        } else {
            listener.onFailure("This user is already registered")
        }
    }
}