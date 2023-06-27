package com.example.buylist2.repositories

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


open class BaseRepository (val context: Context) {

    fun isConnectionAvaliable(): Boolean{

        var result = false

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager
        val activeNet = cm.activeNetwork ?: return false
        val netWorkCapability = cm.getNetworkCapabilities(activeNet) ?: return false

        result = when {
            netWorkCapability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            netWorkCapability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            else -> false
        }
        return result
    }

}