package com.example.tanaman.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.tanaman.BaseApplication
import com.example.tanaman.R
import com.example.tanaman.model.Tanaman
import com.example.tanaman.ui.viewmodel.TanamanViewModel
import com.example.tanaman.ui.viewmodel.TanamanViewModelFactory

class ConfirmationDialogFragment(
    private val tanaman: Tanaman
): DialogFragment() {

    private val viewModel: TanamanViewModel by viewModels {
        TanamanViewModelFactory(
            (activity?.application as BaseApplication).database.tanamanDao()
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
                .setTitle(R.string.cek_rekomendasi)
                .setMessage(R.string.submit_input)
                .setPositiveButton(R.string.yes) { _, _ ->
                    viewModel.insertHistory(
                        tanaman.nama,
                        tanaman.nitrogen,
                        tanaman.phosporus,
                        tanaman.potasium,
                        tanaman.temperature,
                        tanaman.humidity,
                        tanaman.ph,
                        tanaman.rainfall
                    )
                }
                .setNegativeButton(R.string.no) { _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}