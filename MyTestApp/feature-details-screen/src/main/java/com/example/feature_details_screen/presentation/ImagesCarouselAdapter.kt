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
        val imagesFake = listOf<String>(
            "https://www.ixbt.com/mobile/images/samsung-s5/foto/sgs5-0101.jpg",
            "https://mobile-review.com/review/image/samsung/galaxy-s5/color1.jpg",
            newImages[0],
            "https://tech-today.ru/wp-content/uploads/2014/10/01_problemy_samsung_galaxy_s5.jpg",
            "https://ae04.alicdn.com/kf/HTB1I89ycnJYBeNjy1zeq6yhzVXaZ.jpg"
        )
        images = imagesFake
        notifyItemInserted(newImages.size - 1)
    }

}
