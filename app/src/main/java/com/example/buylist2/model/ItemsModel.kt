package com.example.buylist2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemsList")
class ItemsModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "listId")
    var listId: Int = 0

    @ColumnInfo(name = "items")
    var item: String = ""

    @ColumnInfo(name = "total price")
    var totalPrice: Double = 0.0

    @ColumnInfo(name = "price")
    var price: Double = 0.0

    @ColumnInfo(name = "quatity")
    var quantity: Int = 0

    @ColumnInfo(name = "completed")
    var complete: Boolean = false
}