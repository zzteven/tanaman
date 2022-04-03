package com.example.tanaman.data

import androidx.room.*
import com.example.tanaman.model.Tanaman
import kotlinx.coroutines.flow.Flow

@Dao
interface TanamanDao {

    @Query("SELECT * from tanaman_database")
    fun getTanaman(): Flow<List<Tanaman>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tanaman: Tanaman)

    @Query("DELETE from tanaman_database")
    suspend fun deleteAll()
}