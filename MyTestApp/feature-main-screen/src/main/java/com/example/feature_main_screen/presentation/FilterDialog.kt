package com.example.feature_main_screen.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.example.feature_main_screen.databinding.DialogFragmentFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterDialog : BottomSheetDialogFragment() {
    private var _binding: DialogFragmentFilterBinding? = null
    private val binding: DialogFragmentFilterBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentFilterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.priceSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val price = progress * 1000
                val formatted = String.format("%,d", price)
                binding.priceEditableText.text = "$${formatted}.00"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        binding.doneButton.setOnClickListener {
            val size = (binding.sizeSelector.selectedItem as String).split("to").map {
                it.filter { char -> char.isDigit() || char == '.' }.toDouble()
            }
            val price =  binding.priceSeekBar.progress * 1000
            val bundle = Bundle().apply {
                putString("brand", binding.brandSelector.selectedItem as String)
                putInt("price",price)
                putDoubleArray("size", size.toDoubleArray())
            }
            parentFragmentManager.setFragmentResult("filterOptions",bundle)
            dismiss()
        }

        binding.closeButton.setOnClickListener {
            dismiss()
        }

    }


}