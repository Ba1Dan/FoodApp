package com.baiganov.foodapp.bindingadapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.baiganov.foodapp.R
import com.baiganov.foodapp.models.Result
import com.baiganov.foodapp.ui.fragments.recipes.RecipesFragmentDirections

object RecipesRowBinding {

    @BindingAdapter("onRecipeClickListener")
    @JvmStatic
    fun onRecipeClickListener(layout: ConstraintLayout, result: Result) {
        layout.setOnClickListener {
            try {
                val action = RecipesFragmentDirections.actionRecipesFragmentToDetailActivity(result)
                layout.findNavController().navigate(action)
            } catch (e: Exception) {
                Log.d("onRecipeClickListener", e.toString())
            }
        }
    }

    @BindingAdapter("loadImageFromUrl")
    @JvmStatic
    fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl) {
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
    }

    @BindingAdapter("applyVeganColor")
    @JvmStatic
    fun applyVeganColor(view: View, vegan: Boolean) {
        if (vegan) {
            when (view) {
                is TextView -> {
                    view.setTextColor(
                        ContextCompat.getColor(
                            view.context,
                            R.color.green
                        )
                    )
                }
                is ImageView -> {
                    view.setColorFilter(
                        ContextCompat.getColor(
                            view.context,
                            R.color.green
                        )
                    )
                }
            }
        }
    }

}