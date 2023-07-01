package com.example.buylist2.repositories.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.buylist2.model.LoginModel
import com.example.buylist2.repositories.dao.LoginDAO

@Database(entities = [LoginModel::class], version = 1)
abstract class ShoppingDataBase : RoomDatabase() {

    abstract fun loginDao(): LoginDAO

    //Singleton
    companion object {
        private lateinit var INSTANCE: ShoppingDataBase

        fun getDataBase(context: Context): ShoppingDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(ShoppingDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, ShoppingDataBase::class.java, "shoppingdb")
                        .addMigrations(MIGRATION_1_2)
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
    }

    }