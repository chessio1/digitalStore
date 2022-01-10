package com.skillbox.mytestapp.presentation.details

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.helper.widget.Carousel
import com.google.android.material.card.MaterialCardView
import timber.log.Timber

class CarouselAdapter(private val drawables:List<Drawable>): Carousel.Adapter {
    override fun count(): Int {
        return drawables.size
    }
    override fun populate(view: View?, index: Int) {
        Timber.d("HELLO $index")
        val imageView = (view as MaterialCardView).findViewWithTag<ImageView>("image")
        imageView.setImageDrawable(drawables[index])
    }
    override fun onNewItem(index: Int){
        Timber.d("HELLO new index $index")
    }
}