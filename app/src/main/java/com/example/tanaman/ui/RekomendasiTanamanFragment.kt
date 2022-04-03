package com.example.tanaman.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tanaman.BaseApplication
import com.example.tanaman.databinding.FragmentRekomendasiTanamanBinding
import com.example.tanaman.ml.Crop
import com.example.tanaman.model.Tanaman
import com.example.tanaman.ui.viewmodel.TanamanViewModel
import com.example.tanaman.ui.viewmodel.TanamanViewModelFactory
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer

class RekomendasiTanamanFragment : Fragment() {

    private var _binding: FragmentRekomendasiTanamanBinding? = null

    private val binding get() = _binding!!

    private val viewModel: TanamanViewModel by viewModels {
        TanamanViewModelFactory(
            (activity?.application as BaseApplication).database.tanamanDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRekomendasiTanamanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submit.setOnClickListener {
            if (isValidEntry()) {
                val model = Crop.newInstance(requireContext())

                val byte_buffer: ByteBuffer = ByteBuffer.allocateDirect(7 * 4)

                byte_buffer.putFloat(binding.nitrogen.text.toString().toFloat())
                byte_buffer.putFloat(binding.phosporus.text.toString().toFloat())
                byte_buffer.putFloat(binding.potasium.text.toString().toFloat())
                byte_buffer.putFloat(binding.temperature.text.toString().toFloat())
                byte_buffer.putFloat(binding.humidity.text.toString().toFloat())
                byte_buffer.putFloat(binding.ph.text.toString().toFloat())
                byte_buffer.putFloat(binding.rainfall.text.toString().toFloat())

                val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 7), DataType.FLOAT32)
                inputFeature0.loadBuffer(byte_buffer)

                val outputs = model.process(inputFeature0)
                val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

                val nama = when(outputFeature0.asList().indexOf(outputFeature0.maxOrNull())) {
                    0 -> "Anggur"
                    1 -> "Apel"
                    2 -> "Beras"
                    3 -> "Buncis"
                    4 -> "Delima"
                    5 -> "Jagung"
                    6 -> "Jeruk"
                    7 -> "Kacang Hijau"
                    8 -> "Kcang Merah"
                    9 -> "Kacang Ngengat"
                    10 -> "Kacang Polong"
                    11 -> "Kapas"
                    12 -> "Kelapa"
                    13 -> "Kopi"
                    14 -> "Lentil"
                    15 -> "Lentil Hitam"
                    16 -> "Mangga"
                    17 -> "Melon"
                    18 -> "Pepaya"
                    19 -> "Pisang"
                    20 -> "Rami"
                    21 -> "Semangka"
                    else -> ""
                }

                val tanaman = Tanaman(
                    nama = nama,
                    nitrogen = binding.nitrogen.text.toString().toInt(),
                    phosporus = binding.phosporus.text.toString().toInt(),
                    potasium = binding.potasium.text.toString().toInt(),
                    temperature = binding.temperature.text.toString().toDouble(),
                    humidity = binding.humidity.text.toString().toDouble(),
                    ph = binding.ph.text.toString().toDouble(),
                    rainfall = binding.rainfall.text.toString().toDouble()
                )

                model.close()

                ConfirmationDialogFragment(tanaman).show(childFragmentManager, "tanaman")
            }
        }
    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nitrogen,
        binding.phosporus,
        binding.potasium,
        binding.temperature,
        binding.humidity,
        binding.ph,
        binding.rainfall
    )
}