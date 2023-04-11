package com.example.bloconotas.database.dao

import androidx.room.*
import com.example.bloconotas.entities.Nota

@Dao
interface NotaDao {
    @Insert
    fun Insert(nota: Nota)

    @Query("SELECT * FROM Nota")
    fun GetAll(): List<Nota>

    @Query("SELECT * FROM Nota WHERE Id = :id")
    fun GetById(id: Int): Nota

    @Update
    fun Update(nota: Nota)

    @Delete
    fun Delete(id: Int)
}