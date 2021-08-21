package com.baiganov.foodapp.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baiganov.foodapp.adapters.FavouriteRecipesAdapter
import com.baiganov.foodapp.data.database.entities.FavouritesEntity

object FavoriteRecipesBinding {

    @BindingAdapter("viewVisibility", "setData", requireAll = false)
    @JvmStatic
    fun setDataAndViewVisibility(
        view: View,
        favoritesEntity: List<FavouritesEntity>?,
        mAdapter: FavouriteRecipesAdapter?
    ) {
        if (favoritesEntity.isNullOrEmpty()) {
            when (view) {
                is ImageView -> {
                    view.visibility = View.VISIBLE
                }
                is TextView -> {
                    view.visibility = View.VISIBLE
                }
                is RecyclerView -> {
                    view.visibility = View.INVISIBLE
                }
            }
        } else {
            when (view) {
                is ImageView -> {
                    view.visibility = View.INVISIBLE
                }
                is TextView -> {
                    view.visibility = View.INVISIBLE
                }
                is RecyclerView -> {
                    view.visibility = View.VISIBLE
                    mAdapter?.setData(favoritesEntity)
                }
            }
        }
    }
}