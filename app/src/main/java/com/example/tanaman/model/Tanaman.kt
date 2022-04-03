package com.example.tanaman.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tanaman_database")
data class Tanaman(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nama: String = "",
    val deskripsi: String = "",
    val gambar: String = "",
    val nitrogen: Int = 0,
    val phosporus: Int = 0,
    val potasium: Int = 0,
    val temperature: Double = 0.0,
    val humidity: Double = 0.0,
    val ph: Double = 0.0,
    val rainfall: Double = 0.0
)