package com.example.buylist2.model

class ValidationModel(message: String = "") {
    private var status: Boolean = true
    private var validationMessage = ""

    init {
        if(message!= ""){
            validationMessage = message
            status = false
        }
    }

    fun status() = status
    fun message() = validationMessage
}