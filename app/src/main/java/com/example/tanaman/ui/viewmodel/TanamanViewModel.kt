package com.example.tanaman.ui.viewmodel

import android.widget.EditText
import androidx.lifecycle.*
import com.example.tanaman.data.TanamanDao
import com.example.tanaman.model.Tanaman
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TanamanViewModel(private val tanamanDao: TanamanDao) : ViewModel() {

    val tanaman: LiveData<List<Tanaman>> = tanamanDao.getTanaman().asLiveData()

    fun insertHistory(
        nama: String,
        nitrogen: Int,
        phosporus: Int,
        potasium: Int,
        temperature: Double,
        humidity: Double,
        ph: Double,
        rainfall: Double
    ) {
        val tanaman = Tanaman(
            nama = nama,
            nitrogen = nitrogen,
            phosporus = phosporus,
            potasium = potasium,
            temperature = temperature,
            humidity = humidity,
            ph = ph,
            rainfall = rainfall
        )

        viewModelScope.launch(Dispatchers.IO) {
            tanamanDao.insert(tanaman)
        }
    }

    fun deleteAllHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            tanamanDao.deleteAll()
        }
    }

    fun isValidEntry(
        nitrogen: EditText,
        phosporus: EditText,
        potasium: EditText,
        temperature: EditText,
        humidity: EditText,
        ph: EditText,
        rainfall: EditText
    ): Boolean {
        var error = false

        if (nitrogen.text.toString().isBlank()) {
            nitrogen.error = "Nitrogen tidak boleh kosong"
            error = true
        }
        if (phosporus.text.toString().isBlank()) {
            phosporus.error = "Phosporus tidak boleh kosong"
            error = true
        }
        if (potasium.text.toString().isBlank()) {
            potasium.error = "Potasium tidak boleh kosong"
            error = true
        }
        if (temperature.text.toString().isBlank()) {
            temperature.error = "Temperature tidak boleh kosong"
            error = true
        }
        if (humidity.text.toString().isBlank()) {
            humidity.error = "Humidity tidak boleh kosong"
            error = true
        }
        if (ph.text.toString().isBlank()) {
            ph.error = "PH tidak boleh kosong"
            error = true
        }
        if (rainfall.text.toString().isBlank()) {
            rainfall.error = "Rainfall tidak boleh kosong"
            error = true
        }
        return !error
    }
}

class TanamanViewModelFactory(private val tanamanDao: TanamanDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TanamanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TanamanViewModel(tanamanDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}