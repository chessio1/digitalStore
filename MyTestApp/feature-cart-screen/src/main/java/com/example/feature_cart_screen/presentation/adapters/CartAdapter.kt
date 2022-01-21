package com.example.feature_cart_screen.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_cart_screen.data.model.RemoteBasket
import com.example.feature_cart_screen.databinding.ItemCartItemBinding

class CartAdapter(val countChangedCallBack:(count:List<Int>,prices:List<Int>)->Unit) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var baskets = listOf<RemoteBasket>()
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
        val binding = holder.binding
        val item = baskets[position] ?: return
        binding.phoneNameTextView.text = item.title
        binding.priceTextView.text = "$${item.price}"
        setCount(binding, position)
        Glide.with(binding.root).load(item.images).into(binding.cartImage)

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
        counts.remove(position)
        countChangedCallBack(counts.map { it.value },baskets.map { it.price })
        notifyItemRemoved(position)

    }

    override fun getItemCount(): Int {
        return baskets.size
    }

    fun setNewList(basket: List<RemoteBasket>) {
        baskets = basket.mapIndexed { index, basket ->
            basket.copy(id = index)
        }
        baskets.forEachIndexed { index, basket ->
            counts[baskets[index].id!!] = 1
        }
        notifyItemRangeInserted(0,basket.size)
    }

}
