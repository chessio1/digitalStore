package com.example.feature_main_screen.presentation.adapters.delegateAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.feature_main_screen.data.model.DeviceSelectionItem
import com.example.feature_main_screen.data.model.ListItem
import com.example.feature_main_screen.data.model.RemoteBestSeller
import com.example.feature_main_screen.data.model.RemoteHomeStore
import timber.log.Timber

class MyDiffutilItemCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return if (oldItem is DeviceSelectionItem && newItem is DeviceSelectionItem) {
            oldItem.id == newItem.id
        } else if (oldItem is RemoteHomeStore && newItem is RemoteHomeStore) {
            oldItem.id == newItem.id
        } else if (oldItem is RemoteBestSeller && newItem is RemoteBestSeller) {
            oldItem.id == newItem.id
        } else false
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return if (oldItem is DeviceSelectionItem && newItem is DeviceSelectionItem) oldItem == newItem
        else if (oldItem is RemoteHomeStore && newItem is RemoteHomeStore) oldItem == newItem
        else if (oldItem is RemoteBestSeller && newItem is RemoteBestSeller) oldItem == newItem
        else false
    }
}