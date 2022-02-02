package com.example.feature_main_screen.presentation.adapters.delegateAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_main_screen.R
import com.example.feature_main_screen.data.model.DeviceSelectionItem
import com.example.feature_main_screen.data.model.ListItem
import com.example.feature_main_screen.databinding.ItemDeviceSelectionBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import timber.log.Timber

class DeviceSelectDelegateAdapter(val selectItem: (itemId: Int) -> Unit) :
    AbsListItemAdapterDelegate<DeviceSelectionItem, ListItem, DeviceSelectDelegateAdapter.DeviceSelectItemViewHolder>() {

    class DeviceSelectItemViewHolder(
        val binding: ItemDeviceSelectionBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    )

    override fun isForViewType(
        item: ListItem,
        items: MutableList<ListItem>,
        position: Int
    ): Boolean {
        return item is DeviceSelectionItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): DeviceSelectItemViewHolder {
        val binding = ItemDeviceSelectionBinding
            .inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        return DeviceSelectItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        item: DeviceSelectionItem,
        holder: DeviceSelectItemViewHolder,
        payloads: MutableList<Any>
    ) {
        Timber.d("TEST LIST DEVICE BIND")
        val binding = holder.binding
        Glide.with(binding.root).load(item.picture).into(binding.deviceButton)
        binding.deviceTextView.text = item.name
        binding.deviceTextView.isSelected = item.isSelected
        binding.deviceButton.isSelected = item.isSelected
        binding.deviceButton.setOnClickListener {
            selectItem(item.id)
        }
    }
}