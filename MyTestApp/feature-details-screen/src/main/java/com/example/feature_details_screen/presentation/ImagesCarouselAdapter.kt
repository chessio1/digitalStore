package com.example.feature_details_screen.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_details_screen.databinding.ItemCarouselImageBinding


class ImagesCarouselAdapter : RecyclerView.Adapter<ImagesCarouselAdapter.ImageViewHolder>() {

    private var images: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemCarouselImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(holder.binding.root).load(images[position]).into(holder.binding.imageViewImage)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ImageViewHolder(val binding: ItemCarouselImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun addImages(newImages: List<String>) {
        images = newImages
        notifyItemRangeInserted(0, newImages.size - 1)
    }

}
