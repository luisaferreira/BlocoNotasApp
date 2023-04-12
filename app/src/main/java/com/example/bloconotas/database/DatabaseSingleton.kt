package com.example.bloconotas.database

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Room

class DatabaseSingleton private constructor() {
    var db: AppDatabase? = null

    companion object {
        @Volatile
        private var singleton: DatabaseSingleton? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseSingleton {
            if (singleton == null) {
                singleton = DatabaseSingleton()
                singleton!!.db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "nota"
                ).allowMainThreadQueries().build()
            }
            return singleton!!
        }
    }
}