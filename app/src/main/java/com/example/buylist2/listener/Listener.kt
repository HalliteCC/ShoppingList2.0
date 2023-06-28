package com.example.buylist2.listener

interface Listener<S> {

    fun onSuccess(result: S)

    fun onFailure(message: String)
}