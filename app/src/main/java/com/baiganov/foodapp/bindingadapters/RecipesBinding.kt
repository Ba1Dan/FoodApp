package com.baiganov.foodapp.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.baiganov.foodapp.data.database.RecipesEntity
import com.baiganov.foodapp.models.FoodRecipe
import com.baiganov.foodapp.util.NetworkResult

object RecipesBinding {

    @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
    @JvmStatic
    fun handleReadDataErrors(
        view: View,
        apiResponse: NetworkResult<FoodRecipe>?,
        database: List<RecipesEntity>?
    ) {
        when (view) {
            is ImageView -> {
               view.isVisible = apiResponse is NetworkResult.Error && database.isNullOrEmpty()
            }

            is TextView -> {
                view.isVisible = apiResponse is NetworkResult.Error && database.isNullOrEmpty()
                view.text = apiResponse?.message.toString()
            }
         }
    }
}