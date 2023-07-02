package com.example.buylist2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.buylist2.model.LoginModel
import com.example.buylist2.dao.LoginDAO
import com.example.buylist2.dao.ShoppingListDAO
import com.example.buylist2.model.ShoppingListModel


@Database(entities = [LoginModel::class, ShoppingListModel::class], version = 1)
abstract class ShoppingDataBase : RoomDatabase() {

    abstract fun loginDao(): LoginDAO
    abstract fun shoppingDao(): ShoppingListDAO

    //Singleton
    companion object {
        private lateinit var INSTANCE: ShoppingDataBase

        fun getDataBase(context: Context): ShoppingDataBase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(ShoppingDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, ShoppingDataBase::class.java, "shoppingdb")
                        .addMigrations(MIGRATION_1_2)
                        .addMigrations(MIGRATION_1_2_Shopping)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        private val MIGRATION_1_2: Migration = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Login")
            }
        }
        private val MIGRATION_1_2_Shopping: Migration = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Login")
            }
        }
    }

    }