package com.example.tanaman

import android.app.Application
import com.example.tanaman.data.TanamanDatabase

class BaseApplication : Application() {

    val database: TanamanDatabase by lazy { TanamanDatabase.getDatabase(this) }
}