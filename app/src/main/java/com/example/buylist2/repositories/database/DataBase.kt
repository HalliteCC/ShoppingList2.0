package com.example.buylist2.repositories.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.buylist2.model.LoginModel
import com.example.buylist2.repositories.dao.LoginDao

@Database(entities = [LoginModel::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun loginDao(): LoginDao

    //Singleton
    companion object {
        private lateinit var INSTANCE: DataBase

        fun getDataBase(context: Context): DataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(Database::class) {
                    INSTANCE = Room.databaseBuilder(context, DataBase::class.java, "dataBase")
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