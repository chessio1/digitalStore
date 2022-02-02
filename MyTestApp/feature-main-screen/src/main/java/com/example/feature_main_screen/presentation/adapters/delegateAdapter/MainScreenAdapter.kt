package com.example.feature_main_screen.presentation.adapters.delegateAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_main_screen.data.model.DeviceSelectionItem
import com.example.feature_main_screen.data.model.ListItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import timber.log.Timber

class MainScreenAdapter(val onBestsellerClicked: (id: Int) -> Unit = {}) :
    AsyncListDifferDelegationAdapter<ListItem>(MyDiffutilItemCallback()) {

    //val ITEM_CALLBACK = MyDiffutilItemCallback()
    //private val differ = AsyncListDiffer(this,ITEM_CALLBACK)

    init {
        delegatesManager
            .addDelegate(HomeStoreDelegateAdapter())
            .addDelegate(BestSalesDelegateAdapter { onBestsellerClicked(it) })
            .addDelegate(DeviceSelectDelegateAdapter(selectItem = { id ->
                val newItems = differ.currentList.map { item ->
                    val device = item as DeviceSelectionItem
                    if (device.id == id) {
                        device.copy(isSelected = true)
                    } else {
                        if (device.isSelected) {
                            device.copy(isSelected = false)
                        } else {
                            device
                        }
                    }
                }
                differ.submitList(newItems)
            }))
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return delegatesManager.onCreateViewHolder(parent, viewType)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        delegatesManager.onBindViewHolder(differ.currentList,position,holder)
//    }
//
//    override fun getItemCount(): Int {
//        return differ.currentList.size
//    }
//
//    fun setNewList(newList: List<ListItem>) {
//        Timber.d("TEST LIST SUBMITTED =$newList")
//        differ.submitList(newList)
//    }


}