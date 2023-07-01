package com.example.buylist2.repositories.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.buylist2.model.LoginModel
@Dao
interface LoginDAO {

    @Insert
    fun insert(login: LoginModel): Long


    @Query("SELECT * FROM login WHERE email = :email AND password = :password")
    fun getUser(email: String, password: String): LoginModel
}

