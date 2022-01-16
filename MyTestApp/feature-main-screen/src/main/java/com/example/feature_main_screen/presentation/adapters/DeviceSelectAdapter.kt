package com.example.feature_main_screen.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.ItemDeviceSelectionBinding

class DeviceSelectAdapter : RecyclerView.Adapter<DeviceSelectAdapter.DeviceCelectViewHolder>() {

    private val devices = listOf<String>("phones", "desctops", "health", "books", "tools")
    private var selected: String = "phones"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceCelectViewHolder {
        return DeviceCelectViewHolder(
            ItemDeviceSelectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: DeviceCelectViewHolder, position: Int) {

        val item = devices[position]
        val binding = holder.binding
        val icon = when (item) {
            "phones" -> R.drawable.ic_phones
            "desctops" -> R.drawable.ic_desctops
            "health" -> R.drawable.ic_health_devices
            "books" -> R.drawable.ic_books
            else -> R.drawable.ic_phones
        }
        Glide.with(binding.root).load(icon).into(binding.deviceButton)
        binding.deviceTextView.text = item
        binding.deviceTextView.isSelected = item == selected
        binding.deviceButton.isSelected = item == selected

        binding.deviceButton.setOnClickListener {
            val oldSelected = devices.indexOf(selected)
            selected = item
            notifyItemChanged(position)
            notifyItemChanged(oldSelected)
        }


    }
    override fun getItemCount(): Int {
        return devices.size
    }

    class DeviceCelectViewHolder(val binding: ItemDeviceSelectionBinding) :
        RecyclerView.ViewHolder(binding.root)

}
