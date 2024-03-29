package com.example.feature_main_screen.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_main_screen.data.model.RemoteMainScreen
import com.example.feature_main_screen.databinding.PageHomeStoreBinding

class HotSalesAdapter : RecyclerView.Adapter<HotSalesAdapter.DeviceViewHolder>() {

    private var items: RemoteMainScreen? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val binding = PageHomeStoreBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DeviceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        if (items == null) return
        val item = items!!.home_store[position]
        val binding = holder.binding
        if (item.is_new ?: false){
            binding.isNewLabelImageView.visibility = View.VISIBLE
            binding.newTextView.visibility = View.VISIBLE
        }else{
            binding.isNewLabelImageView.visibility = View.INVISIBLE
            binding.newTextView.visibility = View.INVISIBLE
        }
        binding.titleTextView.text = item.title
        binding.additionTextView.text = item.subtitle
        Glide.with(binding.root).load(item.picture).into(binding.pictureImageView)
    }

    override fun getItemCount(): Int {
        return items?.home_store?.size ?: 0
    }

    fun setNewList(mainScreen: RemoteMainScreen) {
        items = mainScreen
        notifyItemRangeChanged(0, mainScreen.home_store.size)
    }

    class DeviceViewHolder(val binding: PageHomeStoreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)

        items = null

    }

}