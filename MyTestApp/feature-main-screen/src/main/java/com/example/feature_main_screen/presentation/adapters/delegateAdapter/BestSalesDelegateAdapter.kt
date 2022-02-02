package com.example.feature_main_screen.presentation.adapters.delegateAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_main_screen.R
import com.example.feature_main_screen.data.model.ListItem
import com.example.feature_main_screen.data.model.RemoteBestSeller
import com.example.feature_main_screen.databinding.ItemBestSellerBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class BestSalesDelegateAdapter(val onCartClicked:(id:Int)->Unit):AbsListItemAdapterDelegate<RemoteBestSeller,ListItem,BestSalesDelegateAdapter.BestSalesViewHolder>() {


    class BestSalesViewHolder(val binding:ItemBestSellerBinding):RecyclerView.ViewHolder(binding.root)
    private var favourites: MutableList<Int> = mutableListOf()

    override fun isForViewType(
        item: ListItem,
        items: MutableList<ListItem>,
        position: Int
    ): Boolean {
        return item is RemoteBestSeller
    }

    override fun onCreateViewHolder(parent: ViewGroup): BestSalesViewHolder {
        val binding = ItemBestSellerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BestSalesViewHolder(binding)
    }

    override fun onBindViewHolder(
        item: RemoteBestSeller,
        holder: BestSalesViewHolder,
        payloads: MutableList<Any>
    ) {
        val binding = holder.binding
        val glide = Glide.with(binding.root)

        with(binding) {

            phoneName.text = item.title
            price.text = "$" + item.discount_price.toString()
            priceOld.text = "$" + item.price_without_discount.toString()

            addToFavouritesButton.setOnClickListener {
                toggleFavourites(item.id)
                glide.load(favouritesIc(item.id))
                    .into(binding.addToFavouritesButton)
            }

            root.setOnClickListener {
                onCartClicked(item.id)
            }

        }

        with(glide) {
            load(item.picture)
                .into(binding.imageView)
            load(favouritesIc(item.id)).into(binding.addToFavouritesButton)
        }
    }

    private fun isFavourites(itemId: Int): Boolean {
        return favourites.contains(itemId)
    }

    private fun favouritesIc(
        itemId: Int
    ): Int {
        return if (isFavourites(itemId)) {
            R.drawable.ic_favourites_filled
        } else {
            R.drawable.ic_favourites_stroke
        }
    }

    private fun toggleFavourites(itemId: Int) {
        if (isFavourites(itemId)) {
            favourites.remove(itemId)
        } else {
            favourites.add(itemId)
        }
    }

}