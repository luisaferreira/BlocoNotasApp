package com.example.bloconotas.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bloconotas.database.dao.NotaDao
import com.example.bloconotas.entities.Nota

@Database(entities = [Nota::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notaDao(): NotaDao
}