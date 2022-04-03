package com.example.tanaman.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tanaman.model.Tanaman

@Database(entities = [Tanaman::class], version = 1, exportSchema = false)
abstract class TanamanDatabase : RoomDatabase() {

    abstract fun tanamanDao(): TanamanDao

    companion object {
        @Volatile
        private var INSTANCE: TanamanDatabase? = null
        fun getDatabase(context: Context): TanamanDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TanamanDatabase::class.java,
                    "tanaman_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
