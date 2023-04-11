package com.example.bloconotas.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Nota (
    @PrimaryKey (autoGenerate = true) val Id: Int?,
    @ColumnInfo (name = "nome_nota") val NomeNota: String,
    @ColumnInfo (name = "nota") val Nota: String)

