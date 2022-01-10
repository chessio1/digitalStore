package com.skillbox.mytestapp.presentation.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Adapter
import android.widget.SeekBar
import android.widget.SpinnerAdapter
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skillbox.mytestapp.R
import com.skillbox.mytestapp.databinding.DialogFragmentFilterBinding
import timber.log.Timber
import java.text.Format

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