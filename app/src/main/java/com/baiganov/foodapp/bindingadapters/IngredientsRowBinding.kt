package com.baiganov.foodapp.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.baiganov.foodapp.R
import com.baiganov.foodapp.util.Constants.Companion.BASE_IMAGE_URL

object IngredientsRowBinding {

    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageUrl: String) {
        imageView.load(BASE_IMAGE_URL + imageUrl) {
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
    }
}