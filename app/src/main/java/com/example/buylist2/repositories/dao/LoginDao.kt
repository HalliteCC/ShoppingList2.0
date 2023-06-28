package com.example.buylist2.repositories.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.buylist2.model.LoginModel


@Dao
interface LoginDao {

    @Insert
    fun insert(login: LoginModel): Int

    @Query("SELECT * FROM Login WHERE email = : email AND password = :password")
    fun getUser (email: String, password: String): LoginModel
}