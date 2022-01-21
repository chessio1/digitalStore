package com.example.feature_main_screen.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_main_screen.R
import com.example.feature_main_screen.data.model.RemoteBestSeller
import com.example.feature_main_screen.databinding.ItemBestSellerBinding

class BestSalesAdapter(val onCartClicked:(id:Int)->Unit) : RecyclerView.Adapter<BestSalesAdapter.BestSalesViewHolder>() {

    private var bestSales: List<RemoteBestSeller> = emptyList()
    private val favourites: MutableList<Int> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSalesViewHolder {
        return BestSalesViewHolder(
            ItemBestSellerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BestSalesViewHolder, position: Int) {
        val binding = holder.binding
        val item = bestSales[position]
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
            load(item.picture).into(binding.imageView)
            load(favouritesIc(item.id)).into(binding.addToFavouritesButton)
        }



    }

    private fun isFavourites(itemId: Int):Boolean{
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

    private fun toggleFavourites(itemId: Int){
        if (isFavourites(itemId)) {
            favourites.remove(itemId)
        } else {
            favourites.add(itemId)
        }
    }



    override fun getItemCount(): Int {
        return bestSales.size
    }

    fun setNewList(bestSeller: List<RemoteBestSeller>) {
        bestSales = bestSeller
        notifyItemRangeInserted(0, bestSeller.size)
    }

    class BestSalesViewHolder(val binding: ItemBestSellerBinding) :
        RecyclerView.ViewHolder(binding.root)
}