package com.example.buylist2.securitypreference

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {

    private val preference: SharedPreferences =
        context.getSharedPreferences("buyList", Context.MODE_PRIVATE)

    //Used to store a value in SharedPreference
    fun store(key: String, value: String) {
        preference.edit().putString(key, value).apply()
    }

    //Used to remove a value in SharedPreference
    fun remove(key: String){
        preference.edit().remove(key).apply()
    }

    //Used to get a value in SharedPreference
    fun get(key: String): String{
        return preference.getString(key, "") ?: ""
    }
}