package com.example.feature_main_screen.presentation.adapters.delegateAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_main_screen.data.model.ListItem
import com.example.feature_main_screen.data.model.RemoteBestSeller
import com.example.feature_main_screen.data.model.RemoteHomeStore
import com.example.feature_main_screen.databinding.ItemBestSellerBinding
import com.example.feature_main_screen.databinding.PageHomeStoreBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class HomeStoreDelegateAdapter:AbsListItemAdapterDelegate<RemoteHomeStore,ListItem,HomeStoreDelegateAdapter.HomeStoreViewHolder>() {


    class HomeStoreViewHolder(val binding: PageHomeStoreBinding):RecyclerView.ViewHolder(binding.root)

    override fun isForViewType(
        item: ListItem,
        items: MutableList<ListItem>,
        position: Int
    ): Boolean {
        return item is RemoteHomeStore
    }

    override fun onCreateViewHolder(parent: ViewGroup): HomeStoreViewHolder {
        val binding = PageHomeStoreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeStoreViewHolder(binding)
    }

    override fun onBindViewHolder(
        item: RemoteHomeStore,
        holder: HomeStoreViewHolder,
        payloads: MutableList<Any>
    ) {
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

}