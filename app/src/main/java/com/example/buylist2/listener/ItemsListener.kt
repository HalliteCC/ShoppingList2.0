package com.example.buylist2.listener

interface ItemsListener {

    //Click para edição
    fun onClick(id: Int)

    //Click para remoção
    fun onDelete (id: Int)

    //Completar tarefa
    fun onCompleteClick(id: Int)

    //Descompletar Tarefa
    fun onUndoClick(id: Int)
}