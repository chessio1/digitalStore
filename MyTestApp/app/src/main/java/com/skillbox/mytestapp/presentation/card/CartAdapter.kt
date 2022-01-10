package com.skillbox.mytestapp.presentation.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.mytestapp.data.models.myCard.Basket
import com.skillbox.mytestapp.databinding.ItemCartItemBinding

import timber.log.Timber

class CartAdapter(val countChangedCallBack:(count:List<Int>,prices:List<Int>)->Unit) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var baskets = listOf<Basket>()
    private val counts = mutableMapOf<Int,Int>()

    class CartViewHolder(val binding: ItemCartItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            ItemCartItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        Timber.d("SUPER $position $baskets")
        val binding = holder.binding
        val item = baskets[position] ?: return
        Timber.d("BINDED $item")
        binding.phoneNameTextView.text = item.title
        binding.priceTextView.text = "$${item.price}"
        setCount(binding, position)
        Glide.with(binding.root).load(item.image).into(binding.cartImage)

        binding.deleteButton.setOnClickListener {
            deleteItem(position)
        }

        binding.minus.setOnClickListener {
            if (counts[position]==0) return@setOnClickListener

            counts[position] = counts[position]!!-1
            setCount(binding, position)
            countChangedCallBack(counts.map { it.value },baskets.map { it.price })
        }

        binding.plus.setOnClickListener {
            counts[position] = counts[position]!!+1
            setCount(binding, position)
            countChangedCallBack(counts.map { it.value },baskets.map { it.price })
        }

    }

    private fun setCount(
        binding: ItemCartItemBinding,
        position: Int
    ) {
        binding.countTextView.text = counts[position].toString()
    }

    private fun deleteItem(position: Int) {
        baskets = baskets.filter { it.id == position }
        Timber.d("$position")
        counts.remove(position)
        countChangedCallBack(counts.map { it.value },baskets.map { it.price })
        Timber.d("$counts $baskets")
        notifyItemRemoved(position)

    }

    override fun getItemCount(): Int {
        return baskets.size
    }

    fun setNewList(basket: List<Basket>) {
        baskets = basket.mapIndexed { index, basket ->
            basket.copy(id = index)
        }
        baskets.forEachIndexed { index, basket ->
            counts[baskets[index].id!!] = 1
        }
        Timber.d("LOADED $baskets")
        notifyItemRangeInserted(0,basket.size)
    }

}
